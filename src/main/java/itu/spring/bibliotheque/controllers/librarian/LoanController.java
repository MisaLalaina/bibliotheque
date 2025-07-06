package itu.spring.bibliotheque.controllers.librarian;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import itu.spring.bibliotheque.enums.BookState;
import itu.spring.bibliotheque.enums.HolidayDirection;
import itu.spring.bibliotheque.models.Adherent;
import itu.spring.bibliotheque.models.AdherentInfo;
import itu.spring.bibliotheque.models.Book;
import itu.spring.bibliotheque.models.Loan;
import itu.spring.bibliotheque.models.Reservation;
import itu.spring.bibliotheque.models.Utilisateur;
import itu.spring.bibliotheque.models.dto.BookReservation;
import itu.spring.bibliotheque.models.form.LoanForm;
import itu.spring.bibliotheque.services.AdherentInfoService;
import itu.spring.bibliotheque.services.AdherentService;
import itu.spring.bibliotheque.services.BookConstraintService;
import itu.spring.bibliotheque.services.BookReservationService;
import itu.spring.bibliotheque.services.BookService;
import itu.spring.bibliotheque.services.HolidayListService;
import itu.spring.bibliotheque.services.LoanService;
import itu.spring.bibliotheque.services.ReservationService;
import itu.spring.bibliotheque.utils.DateUtils;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/librarian/loans")
public class LoanController {
    @Autowired
    private HolidayListService holidayService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private BookConstraintService bookConstraintService;
    @Autowired
    private BookService bookService;
    @Autowired
    private AdherentService adherentService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private  AdherentInfoService adherentInfoService;
    @Autowired
    private BookReservationService bookReservationService;

    @GetMapping("")
    public String getMethodName(HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        model.addAttribute("loans", loanService.findAll());
        return "librarian/loanList"; // Assuming you have a view named loanList.jsp
    }

    @GetMapping("/create")
    public String empruntForm(Model model, @ModelAttribute LoanForm location, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        Book book = null;
        Adherent adherent = null;
        Loan loan = new Loan();

        if (location.getReservationId() != null) {
            Reservation reservation = reservationService.findById(location.getReservationId());
            book = reservation.getBook();
            adherent = reservation.getAdherent();
            loan.setBook(book);
            loan.setAdherent(adherent);
        } else if (location.getBookId() != null) {
            book = bookService.findById(location.getBookId());
            if (BookState.Loaned.name().equals(book.getState())) {
                model.addAttribute("error", "Le livre n'est pas disponible pour l'emprunt.");
            } else if (BookState.Reserved.name().equals(book.getState())) {
                model.addAttribute("error", "Le livre est réservé et ne peut pas être emprunté.");
            }
            loan.setBook(book);
        }
        // If you want to prefill dates, you can set them here
        model.addAttribute("loan", loan);
        model.addAttribute("book", book);
        model.addAttribute("adherent", adherent);
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("adherents", adherentService.findAll());
        model.addAttribute("holidayDirections", HolidayDirection.values());
        return "librarian/loanForm";
    }

    @PostMapping("/save")
    public String saveLoan(@ModelAttribute Loan loan, @RequestParam(required = true) HolidayDirection holiday,  HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        Book book = bookService.findById(loan.getBook().getId());
        Reservation reservation = null;
        Adherent adherent = adherentService.findById(loan.getAdherent().getId());
        if (loan.getFromDate() == null) {
            model.addAttribute("error", "La date de début ne peut pas être vide.");
            return "librarian/loanForm";
        }
        if (book == null) {
            model.addAttribute("error", "Le livre n'existe pas.");
            return "librarian/loanForm";
        }
        if (adherent == null) {
            model.addAttribute("error", "L'adhérent n'existe pas.");
            return "librarian/loanForm";
        }

        try {
            // Confirmation d'une reservation
            if (book.getState().equals(BookState.Reserved.name())) {
                List<BookReservation> books = bookService.findReservedBooksByAdherentId(adherent.getId());
                if (books == null || books.isEmpty()) {
                    throw new IllegalArgumentException("Le livre '"+book.getTitle()+"' est réservé par un autre adherent : " + adherent.getUtilisateur().getUsername());
                }
                for (BookReservation br : books) {
                    if (br.getAdherentId().equals(adherent.getId()) && br.getBookId().equals(book.getId())) {
                        reservation = reservationService.findById(br.getReservationId());
                        break;
                    }
                    throw new IllegalArgumentException("Le livre est réservé par un autre adherent : " + adherent.getUtilisateur().getUsername());
                }
            }
            else if (!book.getState().equals(BookState.Available.name())) {
                throw new IllegalArgumentException("Le livre '"+book.getTitle()+"' n'est pas disponible.");
            }
            // Validation de Adherent et Book
            bookConstraintService.checkAvaiabilityConstraints(adherent, book, loan.getFromDate());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("books", bookService.findAll());
            model.addAttribute("adherents", adherentService.findAll());
            model.addAttribute("holidayDirections", HolidayDirection.values());
            return "librarian/loanForm";
        }

        AdherentInfo adherentInfo = adherentInfoService.findByAdherentId(adherent.getId());
        int day = adherentInfo.getAvailableDuration();
        Date toDate = DateUtils.getLoanEndDate(loan.getFromDate(), day, holiday, holidayService);

        loan.setCreatedBy(user);
        loan.setAdherent(adherent);
        loan.setBook(book);
        loan.setToDate(toDate);
        loanService.create(loan);

        if(reservation != null) {
            bookReservationService.loaned(reservation);
        }
        else {
            bookService.loaned(book);
        }
        return "redirect:/librarian/loans";
    }
}

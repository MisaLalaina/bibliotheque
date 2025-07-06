package itu.spring.bibliotheque.controllers.librarian;

import java.sql.Date;

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
import itu.spring.bibliotheque.models.form.LoanForm;
import itu.spring.bibliotheque.services.AdherentInfoService;
import itu.spring.bibliotheque.services.AdherentService;
import itu.spring.bibliotheque.services.BookService;
import itu.spring.bibliotheque.services.LoanService;
import itu.spring.bibliotheque.services.ReservationService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/librarian/loans")
public class LoanController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BookService bookService;
    @Autowired
    private AdherentService adherentService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private  AdherentInfoService adherentInfoService;

    @GetMapping("")
    public String getMethodName(HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        // Here you would typically fetch the list of loans from the service layer
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
            if (BookState.CHECKED_OUT.getLabel().equals(book.getState())) {
                model.addAttribute("error", "Le livre n'est pas disponible pour l'emprunt.");
            } else if (BookState.RESERVED.getLabel().equals(book.getState())) {
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
        return "librarian/loanForm";
    }

    @PostMapping("/save")
    public String saveLoan(@ModelAttribute Loan loan, @RequestParam(required = true) HolidayDirection holiday,  HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        Adherent adherent = adherentService.findById(loan.getAdherent().getId());
        Book book = bookService.findById(loan.getBook().getId());
        AdherentInfo adherentInfo = adherentInfoService.findByAdherentId(adherent.getId());
        if (loan.getFromDate() == null) {
            model.addAttribute("error", "La date de début ne peut pas être vide.");
            return "librarian/loanForm";
        }
        int day = adherentInfo.getAvailableDuration();
        Date toDate = Date.valueOf(loan.getFromDate().toLocalDate().plusDays(day));

        loan.setCreatedBy(user);
        loan.setAdherent(adherent);
        loan.setBook(book);
        loan.setToDate(toDate);
        loanService.saveLoan(loan);
        
        return "redirect:/librarian/loans";
    }
}

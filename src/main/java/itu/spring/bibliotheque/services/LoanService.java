package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.enums.BookState;
import itu.spring.bibliotheque.enums.HolidayDirection;
import itu.spring.bibliotheque.enums.LoanState;
import itu.spring.bibliotheque.models.Adherent;
import itu.spring.bibliotheque.models.AdherentInfo;
import itu.spring.bibliotheque.models.Book;
import itu.spring.bibliotheque.models.BookCopy;
import itu.spring.bibliotheque.models.Loan;
import itu.spring.bibliotheque.repositories.LoanRepository;
import itu.spring.bibliotheque.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.spring.bibliotheque.models.Config;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import itu.spring.bibliotheque.models.Reservation;
import itu.spring.bibliotheque.models.Sanction;
import itu.spring.bibliotheque.models.Utilisateur;
import itu.spring.bibliotheque.models.dto.BookReservation;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private BookConstraintService bookConstraintService;
    @Autowired
    private AdherentInfoService adherentInfoService;
    @Autowired
    private HolidayListService holidayService;
    @Autowired
    private BookReservationService bookReservationService;
    @Autowired
    private AdherentService adherentService;
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired 
    private SanctionService sanctionService;
    @Autowired
    private ConfigService configService;

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Optional<Loan> findById(Integer id) {
        return loanRepository.findById(id);
    }

    public List<Loan> findLoansByAdherentId(Integer adherentId) {
        return loanRepository.findByAdherentId(adherentId);
    }

    // public List<Loan> findLoansByBookId(Integer bookId) {
    //     return loanRepository.findByBookId(bookId);
    // }

    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    public void delete(Integer id) {
        loanRepository.deleteById(id);
    }

    public Loan create(Loan loan) {
        loan.setState(LoanState.Actif.name());
        return this.save(loan);
    }

    public Loan finish(Loan loan) {
        loan.setState(LoanState.Finished.name());
        
        return this.save(loan);
    }

    public Loan finish(Loan loan, Date returnDate) {
        if (loan.getToDate().before(returnDate)) {
            loan.setState(LoanState.Overdue.name());
            Config c = configService.getConfig();
            Sanction sanction = new Sanction();
            sanction.setAdherent(loan.getAdherent());
            sanction.setFromDate(returnDate);
            Date toDate = Date.valueOf(
                sanction.getFromDate().toLocalDate().plusDays(
                    c.getDefaultSanction()
                )
            );
            sanction.setToDate(toDate);
            sanction.setDuration(c.getDefaultSanction());
            sanctionService.save(sanction);
        } else {
            loan.setState(LoanState.Finished.name());
        }
        return this.save(loan);
    }

    public Loan ovrdue(Loan loan) {
        loan.setState(LoanState.Overdue.name());
        return this.save(loan);
    }

    public Loan innactif(Loan loan) {
        loan.setState(LoanState.Innactif.name());
        return this.save(loan);
    }

    public void createLoanWithReservation(Loan loan, HolidayDirection holiday, Utilisateur user) {
        // Fetch book and adherent
        Book book = bookService.findById(loan.getBookCopy().getBook().getId());
        Adherent adherent = adherentService.findById(loan.getAdherent().getId());
        if (loan.getFromDate() == null) {
            throw new IllegalArgumentException("La date de début ne peut pas être vide.");
        }
        if (book == null) {
            throw new IllegalArgumentException("Le livre n'existe pas.");
        }
        if (adherent == null) {
            throw new IllegalArgumentException("L'adhérent n'existe pas.");
        }
        Reservation reservation = null;
        // Reservation logic
        List<BookReservation> books = bookService.findReservedBooksByAdherentId(adherent.getId());
        for (BookReservation br : books) {
            if (br.getAdherentId().equals(adherent.getId()) && br.getBookId().equals(book.getId())) {
                reservation = reservationService.findById(br.getReservationId());
                break;
            }
            throw new IllegalArgumentException("Le livre est réservé par un autre adherent : " + adherent.getUtilisateur().getUsername());
        }
        boolean res = reservation != null;
        // Validation de Adherent et Book
        BookCopy bookCopy = bookConstraintService.checkAvaiabilityConstraints(adherent, book, loan.getFromDate(), res);

        AdherentInfo adherentInfo = adherentInfoService.findByAdherentId(adherent.getId());
        int day = adherentInfo.getAvailableDuration();
        Date toDate = DateUtils.getLoanEndDate(loan.getFromDate(), day, holiday, holidayService);
        loan.setCreatedBy(user);
        loan.setAdherent(adherent);
        loan.setBookCopy(bookCopy);
        loan.setToDate(toDate);
        this.create(loan);
        if(reservation != null) {
            bookReservationService.loaned(reservation, bookCopy);
        } else {
            // bookService.loaned(book);
            bookCopyService.loaned(bookCopy);
        }
    }

    public Loan extendToDate(Loan loan, int day, HolidayDirection holiday) {
        Date toDate = DateUtils.getLoanEndDate(loan.getToDate(), day, holiday, holidayService);
        loan.setToDate(toDate);
        return loan;
    }
}

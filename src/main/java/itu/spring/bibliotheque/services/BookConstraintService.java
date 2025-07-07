package itu.spring.bibliotheque.services;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import itu.spring.bibliotheque.enums.BookState;
import itu.spring.bibliotheque.models.Adherent;
import itu.spring.bibliotheque.models.AdherentInfo;
import itu.spring.bibliotheque.models.Book;
import itu.spring.bibliotheque.models.BookCopy;
import itu.spring.bibliotheque.models.Subscription;
import itu.spring.bibliotheque.models.dto.BookLoan;
import itu.spring.bibliotheque.models.dto.BookReservation;

@Service
public class BookConstraintService {

    @Autowired
    private SanctionService sanctionService;
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private AdherentInfoService adherentInfoService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookCopyService bookCopyService;

    public BookCopy checkReservationConstraints(Adherent adherent, Integer bookId, Date refDate) {
        Book book = bookService.findById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }
        return checkAvaiabilityConstraints(adherent, book, refDate);
    }

    public void checkSanctions(Adherent adherent, Date refDate) {
        boolean check = sanctionService.checkSanction(adherent.getId(), refDate);
        if (check) {
            throw new RuntimeException("The Adherent is sanctioned");
        }
    }

    public BookCopy checkAvailableCopy(Book book) {
        return checkAvailableCopy(book, false);
    }
    public BookCopy checkAvailableCopy(Book book, boolean res) {
        BookCopy copy = null;
        List<BookCopy> copies = bookCopyService.findByBookId(book.getId());
        int free = 0;
        for (BookCopy bookCopy : copies) {
            if (res && bookCopy.getState().equals(BookState.Reserved.name())) {
                free += 1;
                copy = bookCopy;
            }
            else if (bookCopy.getState().equals(BookState.Available.name())) {
                free += 1;
                if (!res) {
                    copy = bookCopy;
                }
            }
        }
        if (free == 0) {
            throw new IllegalArgumentException("The book has no available copy");
        }
        return copy;
    }

    public Book checkBookEligibility(Adherent adherent, Book book,Date refDate){
        if (adherent.getAge(refDate) < book.getAgeMin()) {
            throw new IllegalArgumentException("Adherent must be at least "+book.getAgeMin()+" years old to borrow this book.");
        }
        return book;
    }

    public Subscription checkSubscription(Adherent adherent, Date refDate){
        Subscription subscription = subscriptionService.fincdByAdherentId(adherent.getId(), refDate);
        if (subscription == null) {
            throw new IllegalArgumentException("Adherent does not have a valid subscription.");
        }
        return subscription;
    }

    public AdherentInfo checkQuota(Adherent adherent) {
        List<BookLoan> loans = bookService.findLoanedBooksByAdherentId(adherent.getId());
        List<BookReservation> reservations = bookService.findReservedBooksByAdherentId(adherent.getId());
        AdherentInfo adherentInfo = adherentInfoService.findByAdherentId(adherent.getId());
        int current = reservations.size() + loans.size();
        if (adherentInfo == null) {
            throw new IllegalArgumentException("Adherent does not have valid information.");   
        }
        else if(current >= adherentInfo.getAvailableQuote()) {
        // else if (current > 0) {
            throw new IllegalArgumentException("Adherent has reached the borrowing quota.");
        }
        return adherentInfo;
    }

    public BookCopy findReservedBook(Book book){
        BookCopy copy = null;
        List<BookCopy> copies = bookCopyService.findByBookId(book.getId());
        int free = 0;
        for (BookCopy bookCopy : copies) {
            if (bookCopy.getState().equals(BookState.Reserved.name())) {
                free += 1;
                copy = bookCopy;
            }
        }
        if (free == 0) {
            throw new IllegalArgumentException("The book has no available copy");
        }
        return copy;
    }

    public BookCopy checkAvaiabilityConstraints(Adherent adherent, Book book,  Date refDate) {
        return checkAvaiabilityConstraints(adherent, book, refDate, false);
    }
    public BookCopy checkAvaiabilityConstraints(Adherent adherent, Book book,  Date refDate, boolean res) {
        BookCopy copy = checkAvailableCopy(book, res);
        checkSanctions(adherent, refDate);
        checkSubscription(adherent, refDate);
        checkBookEligibility(adherent, book, refDate);
        checkQuota(adherent);
        return copy;
    }
}

package itu.spring.bibliotheque.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.spring.bibliotheque.models.Adherent;
import itu.spring.bibliotheque.models.AdherentInfo;
import itu.spring.bibliotheque.models.Book;
import itu.spring.bibliotheque.models.Reservation;
import itu.spring.bibliotheque.models.Subscription;

@Service
public class BookConstraintService {
    @Autowired
    private AdherentService adherentService;
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private AdherentInfoService adherentInfoService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private BookService bookService;

    public Book checkReservationConstraints(Adherent adherent, Integer bookId, Date refDate) {
        Book book = bookService.findById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }
        return checkAvaiabilityConstraints(adherent, book, refDate);
    }
    public Book checkAvaiabilityConstraints(Adherent adherent, Book book,  Date refDate) {
        Subscription subscription = subscriptionService.fincdByAdherentId(adherent.getId(), refDate);
        if (subscription == null) {
            throw new IllegalArgumentException("Adherent does not have a valid subscription.");
        }
        // Implement age check here
        if (adherent.getAge(refDate) < book.getAgeMin()) {
            throw new IllegalArgumentException("Adherent must be at least "+book.getAgeMin()+" years old to borrow this book.");
        }
        // Quota
        List<Reservation> reservations = reservationService.findByAdherentId(adherent.getId());
        AdherentInfo adherentInfo = adherentInfoService.findByAdherentId(adherent.getId());
        int current = reservations.size();
        if (adherentInfo == null) {
            throw new IllegalArgumentException("Adherent does not have valid information.");   
        }
        // else (current >= adherentInfo.getAvailableQuote()) {
        else if (current > 0) {
            throw new IllegalArgumentException("Adherent has reached the borrowing quota.");
        }

        return book;
    }
}

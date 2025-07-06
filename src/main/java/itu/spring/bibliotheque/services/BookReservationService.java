package itu.spring.bibliotheque.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.spring.bibliotheque.models.Adherent;
import itu.spring.bibliotheque.models.Book;
import itu.spring.bibliotheque.models.Reservation;
import itu.spring.bibliotheque.models.Utilisateur;

@Service
public class BookReservationService {
    
    @Autowired
    private BookService bookService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private BookConstraintService bookConstraintService;

    public void expire(Reservation reservation){
        if (reservation != null) {
            reservationService.expire(reservation);
            bookService.free(reservation.getBook());
        } else {
            throw new IllegalArgumentException("Reservation cannot be null.");
        }
    }

    public void cancel(Reservation reservation) {
        if (reservation != null) {
            reservationService.cancel(reservation);
            bookService.free(reservation.getBook());
        } else {
            throw new IllegalArgumentException("Reservation cannot be null.");
        }
    }

    public void loaned(Reservation reservation) {
        if (reservation != null) {
            reservationService.loaned(reservation);
            bookService.loaned(reservation.getBook());
        } else {
            throw new IllegalArgumentException("Reservation cannot be null.");
        }
    }

    public void validate(Reservation reservation, Utilisateur user) {
        if (reservation != null) {
            reservationService.validate(reservation, user);
            bookService.reserved(reservation.getBook());
        } else {
            throw new IllegalArgumentException("Reservation cannot be null.");
        }
    }

    public Reservation createReservationWithConstraints(Adherent adherent, Integer bookId, Date reservationDate) {
        // You may want to inject BookService and BookConstraintService if not already
        Book book = bookService.findById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }
        // Check constraints (delegated to BookConstraintService)
        bookConstraintService.checkReservationConstraints(adherent, bookId, reservationDate);
        // Save reservation
        return reservationService.save(adherent, book, reservationDate);
    }
}

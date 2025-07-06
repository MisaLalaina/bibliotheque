package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.enums.ReservationState;
import itu.spring.bibliotheque.models.Adherent;
import itu.spring.bibliotheque.models.Book;
import itu.spring.bibliotheque.models.Reservation;
import itu.spring.bibliotheque.models.Utilisateur;
import itu.spring.bibliotheque.repositories.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation findById(String id) {
        return findById(Integer.parseInt(id));
    }
    public Reservation findById(Integer id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        return reservationOptional.orElse(null); // Return null if not found
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation save(Adherent adherent, Book book, Date reservationDate) {
        Reservation reservation = new Reservation();
        reservation.setAdherent(adherent);
        reservation.setBook(book);
        reservation.setReservationDate(reservationDate);
        reservation.setState(ReservationState.Pending.name());
        return reservationRepository.save(reservation);
    }

    public void deleteById(Integer id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> findByAdherentId(Integer adherentId) {
        return reservationRepository.findByAdherentId(adherentId);
    }

    public Reservation validate(int reservationId, Utilisateur user) {
        Reservation reservation = findById(reservationId);
        if (reservation != null) {
            return validate(reservation, user);
        }
        throw new IllegalArgumentException("Reservation not found with id: " + reservationId);

    }

    
    public Reservation findReservation(Book book) {
        List<Reservation> reservations = reservationRepository.findByBookId(book.getId());
        if (!reservations.isEmpty()) {
            return reservations.get(0); // Return the first reservation found
        }
        return null; // or throw an exception if preferred
    }
    
    public Reservation validate(Reservation reservation, Utilisateur user) {
        reservation.setState(ReservationState.Validated.name());
        reservation.setValidatedBy(user);
        return save(reservation);
    }

    public Reservation expire(Reservation reservation) {
        reservation.setState(ReservationState.Expired.name());
        return save(reservation);
    }

    public Reservation loaned(Reservation reservation) {
        reservation.setState(ReservationState.Loaned.name());
        return save(reservation);
    }

    public Reservation cancel(Reservation reservation) {
        reservation.setState(ReservationState.Canceled.name());
        return save(reservation);
    }

}

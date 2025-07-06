package itu.spring.bibliotheque.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import itu.spring.bibliotheque.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE b.state <> 'AVAILABLE' AND (EXISTS (SELECT l FROM Loan l WHERE l.book = b AND l.adherent.id = ?1))")
    List<Book> findLoanedBooksByAdherentId(Integer adherentId);

    @Query("SELECT b FROM Book b WHERE b.state <> 'AVAILABLE' AND (EXISTS (SELECT r FROM Reservation r WHERE r.book = b AND r.adherent.id = ?1 AND r.state = ReservationState.VALIDATED))")
    List<Book> findReservedBooksByAdherentId(Integer adherentId);
}

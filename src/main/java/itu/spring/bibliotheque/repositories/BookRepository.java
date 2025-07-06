package itu.spring.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import itu.spring.bibliotheque.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @org.springframework.data.jpa.repository.Query("SELECT b FROM Book b WHERE b.state <> 'AVAILABLE' AND (EXISTS (SELECT r FROM Reservation r WHERE r.book = b AND r.adherent.id = ?1) OR EXISTS (SELECT l FROM Loan l WHERE l.book = b AND l.adherent.id = ?1))")
    java.util.List<Book> findBooksNotAvailableByAdherentId(Integer adherentId);

}

package itu.spring.bibliotheque.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import itu.spring.bibliotheque.models.Book;
import itu.spring.bibliotheque.models.dto.BookLoan;
import itu.spring.bibliotheque.models.dto.BookReservation;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT * FROM v_book_loan WHERE adherent_id = ?1", nativeQuery = true)
    List<BookLoan> findLoanedBooksByAdherentId(Integer adherentId);

    @Query(value = "SELECT * FROM v_book_reservation WHERE adherent_id = ?1", nativeQuery = true)
    List<BookReservation> findReservedBooksByAdherentId(Integer adherentId);
}

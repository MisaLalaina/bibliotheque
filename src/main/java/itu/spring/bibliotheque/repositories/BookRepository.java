package itu.spring.bibliotheque.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import itu.spring.bibliotheque.models.Book;
import itu.spring.bibliotheque.models.dto.BookLoan;
import itu.spring.bibliotheque.models.dto.BookReservation;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT * FROM v_book_loan WHERE adherent_id = ?1 AND loan_state = 'Actif'", nativeQuery = true)
    List<BookLoan> findLoanedBooksByAdherentId(Integer adherentId);

    @Query(value = "SELECT * FROM v_book_loan WHERE loan_state = 'Actif'", nativeQuery = true)
    List<BookLoan> findLoanedBooks();

    @Query(value = "SELECT * FROM v_book_reservation WHERE adherent_id = ?1 AND reservation_state = 'Validee'", nativeQuery = true)
    List<BookReservation> findReservedBooksByAdherentId(Integer adherentId);
}

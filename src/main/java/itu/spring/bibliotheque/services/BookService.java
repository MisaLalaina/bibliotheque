package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.enums.BookState;
import itu.spring.bibliotheque.models.Book;
import itu.spring.bibliotheque.models.dto.BookLoan;
import itu.spring.bibliotheque.models.dto.BookReservation;
import itu.spring.bibliotheque.repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(String id) {
        return findById(Integer.parseInt(id)); // Convert String id to Integer
    }
    public Book findById(Integer id) {
        Optional<Book> bOptional = bookRepository.findById(id);
        if (bOptional.isPresent()) {
            return bOptional.get();
        } else {
            return null; // Return an empty Optional if not found
        }
    }

    public List<BookLoan> findLoanedBooksByAdherentId(Integer adherentId) {
        return bookRepository.findLoanedBooksByAdherentId(adherentId);
    }

    public List<BookLoan> findLoanedBooks() {
        return bookRepository.findLoanedBooks();
    }

    public List<BookReservation> findReservedBooksByAdherentId(Integer adherentId) {
        return bookRepository.findReservedBooksByAdherentId(adherentId);
    }



    
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    /**
     * 
     * STATE MANAGEMENT
     * 
     */
    public Book create(Book book) {
        return this.free(book);
    }
    public Book free(Book book) {
        book.setState(BookState.Disponible.name());
        return this.save(book);
    }

    public Book reserved(Book book) {
        book.setState(BookState.Reserver.name());
        return this.save(book);
    }

    public Book loaned(Book book) {
        book.setState(BookState.Emprunter.name());
        return this.save(book);
    }
}

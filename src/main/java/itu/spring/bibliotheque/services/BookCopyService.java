package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.enums.BookState;
import itu.spring.bibliotheque.models.Book;
import itu.spring.bibliotheque.models.BookCopy;
import itu.spring.bibliotheque.repositories.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookCopyService {
    @Autowired
    private BookCopyRepository bookCopyRepository;

    public List<BookCopy> findAll() {
        return bookCopyRepository.findAll();
    }

    public List<BookCopy> findByBookId(Integer bookId) {
        return bookCopyRepository.findByBookId(bookId);
    }

    public Optional<BookCopy> findById(Integer id) {
        return bookCopyRepository.findById(id);
    }

    public BookCopy save(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public void deleteById(Integer id) {
        bookCopyRepository.deleteById(id);
    }

        /**
     * 
     * STATE MANAGEMENT
     * 
     */
    public BookCopy create(BookCopy book) {
        return this.free(book);
    }
    public BookCopy free(BookCopy book) {
        book.setState(BookState.Available.name());
        return this.save(book);
    }

    public BookCopy reserved(BookCopy book) {
        book.setState(BookState.Reserved.name());
        return this.save(book);
    }

    public BookCopy loaned(BookCopy book) {
        book.setState(BookState.Loaned.name());
        return this.save(book);
    }
}

package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.enums.BookState;
import itu.spring.bibliotheque.models.Book;
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

    public Book create(Book book) {
        return this.free(book);
    }
    public Book free(Book book) {
        book.setState(BookState.Available.name());
        return this.save(book);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    public Book reserved(Book book) {
        book.setState(BookState.Reserved.name());
        return this.save(book);
    }

    public Book checkedOut(Book book) {
        book.setState(BookState.Checked_Out.name());
        return this.save(book);
    }
}

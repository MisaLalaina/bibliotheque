package itu.spring.bibliotheque.services;

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
}

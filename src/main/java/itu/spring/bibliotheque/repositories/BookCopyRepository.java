package itu.spring.bibliotheque.repositories;

import itu.spring.bibliotheque.models.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Integer> {
    List<BookCopy> findByBookId(Integer bookId);
}

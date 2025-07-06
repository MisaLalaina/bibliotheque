package itu.spring.bibliotheque.repository;

import itu.spring.bibliotheque.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}

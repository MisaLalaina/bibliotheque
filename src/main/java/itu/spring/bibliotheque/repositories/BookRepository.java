package itu.spring.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import itu.spring.bibliotheque.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}

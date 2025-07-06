package itu.spring.bibliotheque.repository;

import itu.spring.bibliotheque.models.ReturnBook;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReturnBookRepository extends JpaRepository<ReturnBook, Integer> {
    List<ReturnBook> findByAdherentId(Integer adherentId);
    List<ReturnBook> findByState(String state);
}

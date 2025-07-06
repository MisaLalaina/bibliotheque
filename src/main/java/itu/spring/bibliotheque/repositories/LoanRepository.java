package itu.spring.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import itu.spring.bibliotheque.models.Loan;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByAdherentId(Integer adherentId);
    List<Loan> findByBookId(Integer bookId);
}

package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.models.Loan;
import itu.spring.bibliotheque.repositories.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Optional<Loan> findById(Integer id) {
        return loanRepository.findById(id);
    }

    public List<Loan> findLoansByAdherentId(Integer adherentId) {
        return loanRepository.findByAdherentId(adherentId);
    }

    public List<Loan> findLoansByBookId(Integer bookId) {
        return loanRepository.findByBookId(bookId);
    }

    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    public void delete(Integer id) {
        loanRepository.deleteById(id);
    }
}

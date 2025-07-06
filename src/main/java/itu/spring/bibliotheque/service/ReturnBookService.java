package itu.spring.bibliotheque.service;

import itu.spring.bibliotheque.models.ReturnBook;
import itu.spring.bibliotheque.repository.ReturnBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReturnBookService {
    @Autowired
    private ReturnBookRepository returnBookRepository;

    public List<ReturnBook> getAll() {
        return returnBookRepository.findAll();
    }

    public List<ReturnBook> getByAdherentId(Integer adherentId) {
        return returnBookRepository.findByAdherentId(adherentId);
    }

    public List<ReturnBook> getByState(String state) {
        return returnBookRepository.findByState(state);
    }

    public Optional<ReturnBook> getById(Integer id) {
        return returnBookRepository.findById(id);
    }

    public ReturnBook save(ReturnBook returnBook) {
        return returnBookRepository.save(returnBook);
    }

    public void delete(Integer id) {
        returnBookRepository.deleteById(id);
    }
}

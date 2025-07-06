package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.models.Extension;
import itu.spring.bibliotheque.repositories.ExtensionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExtensionService {
    @Autowired
    private ExtensionRepository extensionRepository;

    public List<Extension> getAll() {
        return extensionRepository.findAll();
    }

    public List<Extension> getByLoanId(Integer loanId) {
        return extensionRepository.findByLoanId(loanId);
    }

    public Optional<Extension> getById(Integer id) {
        return extensionRepository.findById(id);
    }

    public Extension save(Extension extension) {
        return extensionRepository.save(extension);
    }

    public void delete(Integer id) {
        extensionRepository.deleteById(id);
    }
}

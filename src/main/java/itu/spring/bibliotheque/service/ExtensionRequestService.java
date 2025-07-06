package itu.spring.bibliotheque.service;

import itu.spring.bibliotheque.models.ExtensionRequest;
import itu.spring.bibliotheque.repository.ExtensionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExtensionRequestService {
    @Autowired
    private ExtensionRequestRepository extensionRequestRepository;

    public List<ExtensionRequest> getAll() {
        return extensionRequestRepository.findAll();
    }

    public List<ExtensionRequest> getByLoanId(Integer loanId) {
        return extensionRequestRepository.findByLoanId(loanId);
    }

    public List<ExtensionRequest> getByState(String state) {
        return extensionRequestRepository.findByState(state);
    }

    public Optional<ExtensionRequest> getById(Integer id) {
        return extensionRequestRepository.findById(id);
    }

    public ExtensionRequest save(ExtensionRequest extensionRequest) {
        return extensionRequestRepository.save(extensionRequest);
    }

    public void delete(Integer id) {
        extensionRequestRepository.deleteById(id);
    }
}

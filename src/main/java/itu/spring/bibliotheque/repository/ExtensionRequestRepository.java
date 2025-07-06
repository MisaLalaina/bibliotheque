package itu.spring.bibliotheque.repository;

import itu.spring.bibliotheque.models.ExtensionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExtensionRequestRepository extends JpaRepository<ExtensionRequest, Integer> {
    List<ExtensionRequest> findByLoanId(Integer loanId);
    List<ExtensionRequest> findByState(String state);
}

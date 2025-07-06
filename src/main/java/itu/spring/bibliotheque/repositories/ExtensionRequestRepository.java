package itu.spring.bibliotheque.repositories;

import itu.spring.bibliotheque.models.ExtensionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExtensionRequestRepository extends JpaRepository<ExtensionRequest, Integer> {
    List<ExtensionRequest> findByLoanId(Integer loanId);
    List<ExtensionRequest> findByState(String state);
    // Custom query to get all extension requests for a user (adherent)
    List<ExtensionRequest> findByLoan_Adherent_Utilisateur_Id(Integer userId);
}

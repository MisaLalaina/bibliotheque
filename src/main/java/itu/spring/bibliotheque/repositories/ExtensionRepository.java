package itu.spring.bibliotheque.repositories;

import itu.spring.bibliotheque.models.Extension;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExtensionRepository extends JpaRepository<Extension, Integer> {
    List<Extension> findByLoanId(Integer loanId);
}

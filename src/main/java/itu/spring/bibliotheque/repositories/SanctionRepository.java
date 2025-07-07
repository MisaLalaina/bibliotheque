package itu.spring.bibliotheque.repositories;

import itu.spring.bibliotheque.models.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SanctionRepository extends JpaRepository<Sanction, Integer> {
    List<Sanction> findByAdherentId(Integer adherentId);
}

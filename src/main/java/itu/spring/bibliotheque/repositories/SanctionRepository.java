package itu.spring.bibliotheque.repositories;

import itu.spring.bibliotheque.models.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SanctionRepository extends JpaRepository<Sanction, Integer> {
    List<Sanction> findByAdherentId(Integer adherentId);
    List<Sanction> findByState(String state);
}

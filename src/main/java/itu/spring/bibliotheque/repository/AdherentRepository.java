package itu.spring.bibliotheque.repository;

import itu.spring.bibliotheque.model.Adherent;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
    Optional<Adherent> findByUtilisateurId(Integer utilisateurId);
}

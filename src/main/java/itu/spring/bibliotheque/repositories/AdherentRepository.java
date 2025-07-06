package itu.spring.bibliotheque.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import itu.spring.bibliotheque.models.Adherent;

public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
    Optional<Adherent> findByUtilisateurId(Integer utilisateurId);
}

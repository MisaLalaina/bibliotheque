package itu.spring.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import itu.spring.bibliotheque.models.Utilisateur;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByUsername(String username);
}

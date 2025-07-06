package itu.spring.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import itu.spring.bibliotheque.models.AdherentType;

public interface AdherentTypeRepository extends JpaRepository<AdherentType, Integer> {
}

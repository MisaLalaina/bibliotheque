package itu.spring.bibliotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itu.spring.bibliotheque.model.AdherentInfo;

public interface AdherentInfoRepository extends JpaRepository<AdherentInfo, Integer> {
}

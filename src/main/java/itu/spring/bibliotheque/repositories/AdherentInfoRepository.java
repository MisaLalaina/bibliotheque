package itu.spring.bibliotheque.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import itu.spring.bibliotheque.models.AdherentInfo;

public interface AdherentInfoRepository extends JpaRepository<AdherentInfo, Integer> {
    Optional<AdherentInfo> findByAdherentId(Integer id);
}

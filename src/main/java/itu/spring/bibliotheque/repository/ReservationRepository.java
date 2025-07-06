package itu.spring.bibliotheque.repository;

import itu.spring.bibliotheque.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByAdherentId(Integer adherentId);
}

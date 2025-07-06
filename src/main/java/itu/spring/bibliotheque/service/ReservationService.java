package itu.spring.bibliotheque.service;

import itu.spring.bibliotheque.model.Reservation;
import itu.spring.bibliotheque.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(Integer id) {
        return reservationRepository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteById(Integer id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> findByAdherentId(Integer adherentId) {
        return reservationRepository.findByAdherentId(adherentId);
    }
}

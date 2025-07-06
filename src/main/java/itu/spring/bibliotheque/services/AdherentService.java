package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.models.Adherent;
import itu.spring.bibliotheque.repositories.AdherentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdherentService {
    @Autowired
    private AdherentRepository adherentRepository;

    public List<Adherent> findAll() {
        return adherentRepository.findAll();
    }

    public Adherent findById(Integer id) {
        Optional<Adherent> adOptional = adherentRepository.findById(id);
        return adOptional.orElse(null); // Return null if not found
    }

    public Adherent save(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    public void deleteById(Integer id) {
        adherentRepository.deleteById(id);
    }

    public Adherent findByUserId(Integer userId) {
        Optional<Adherent> adherent = adherentRepository.findByUtilisateurId(userId);
        if (adherent.isPresent()) {
            return adherent.get();
        } else {
            return null; // or throw an exception if preferred
        }
    }

    
}

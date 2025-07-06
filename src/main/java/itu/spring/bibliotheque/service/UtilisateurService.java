package itu.spring.bibliotheque.service;

import itu.spring.bibliotheque.model.Utilisateur;
import itu.spring.bibliotheque.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Optional<Utilisateur> findByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    public Iterable<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> findById(Integer id) {
        return utilisateurRepository.findById(id);
    }

    public void deleteById(Integer id) {
        utilisateurRepository.deleteById(id);
    }
}

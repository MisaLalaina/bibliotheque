package itu.spring.bibliotheque.service;

import itu.spring.bibliotheque.model.Adherent;
import itu.spring.bibliotheque.model.Book;
import itu.spring.bibliotheque.model.Subscription;
import itu.spring.bibliotheque.repository.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdherentService {
    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    public List<Adherent> findAll() {
        return adherentRepository.findAll();
    }

    public Optional<Adherent> findById(Integer id) {
        return adherentRepository.findById(id);
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

    public void checkAgeAndSubscription(Adherent adherent, Book book, Date refDate) {
        Optional<Subscription> subscription = subscriptionService.fincdByAdherentId(adherent.getId(), refDate);
        if (!subscription.isPresent()) {
            throw new IllegalArgumentException("Adherent does not have a valid subscription.");
        }
        // Implement age check here
        if (adherent.getAge() < book.getAgeMin()) {
            throw new IllegalArgumentException("Adherent must be at least "+book.getAgeMin()+" years old to borrow this book.");
        }
    }
}

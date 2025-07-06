package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.models.Subscription;
import itu.spring.bibliotheque.repositories.SubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> findById(Integer id) {
        return subscriptionRepository.findById(id);
    }

    public Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public void deleteById(Integer id) {
        subscriptionRepository.deleteById(id);
    }

    public List<Subscription> findByAdherentId(Integer adherentId) {
        return subscriptionRepository.findByAdherentId(adherentId);
    }

    public Subscription fincdByAdherentId(Integer adherentId, Date date) {
        List<Subscription> subs = subscriptionRepository.findClosestSubscription(adherentId, date);
        if (subs.size() > 0) {
           return subs.get(0); // Return the most recent subscription
        }
        return null; // or throw an exception if preferred
    }
}

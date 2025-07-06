package itu.spring.bibliotheque.service;

import itu.spring.bibliotheque.model.Subscription;
import itu.spring.bibliotheque.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

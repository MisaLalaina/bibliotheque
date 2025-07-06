package itu.spring.bibliotheque.repository;

import itu.spring.bibliotheque.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
}

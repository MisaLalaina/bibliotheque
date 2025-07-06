package itu.spring.bibliotheque.repository;

import itu.spring.bibliotheque.model.Subscription;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    List<Subscription> findByAdherentId(Integer adherentId);
    
    @Query("""
        SELECT s FROM Subscription s
        WHERE s.adherent.id = :adherentId AND s.startDate <= :date AND s.endDate >= :date
        ORDER BY s.startDate DESC
        LIMIT 1
    """)
    Optional<Subscription> findClosestSubscription(Integer adherentId, Date date);
}

package itu.spring.bibliotheque.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import itu.spring.bibliotheque.models.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    List<Subscription> findByAdherentId(Integer adherentId);
    
    @Query("SELECT s FROM Subscription s WHERE s.adherent.id = ?1 AND s.fromDate <= ?2 AND s.toDate >= ?2 ORDER BY s.fromDate DESC")
    List<Subscription> findClosestSubscription(Integer adherentId, Date date);
}

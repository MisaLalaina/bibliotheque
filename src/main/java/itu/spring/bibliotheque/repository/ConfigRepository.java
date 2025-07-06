package itu.spring.bibliotheque.repository;

import itu.spring.bibliotheque.models.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, Integer> {
}

package itu.spring.bibliotheque.repository;

import itu.spring.bibliotheque.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}

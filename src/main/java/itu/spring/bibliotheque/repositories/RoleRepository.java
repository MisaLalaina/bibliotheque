package itu.spring.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import itu.spring.bibliotheque.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}

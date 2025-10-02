package com.vitalx.apizoo.adapters.repositories;

import com.vitalx.apizoo.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepositoryJpa extends JpaRepository<Animal, String> {
}

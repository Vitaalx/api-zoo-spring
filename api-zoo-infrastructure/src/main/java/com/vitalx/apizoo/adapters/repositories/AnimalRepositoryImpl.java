package com.vitalx.apizoo.adapters.repositories;

import com.vitalx.apizoo.entities.Animal;
import com.vitalx.apizoo.repositories.AnimalRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository {
    private final AnimalRepository animalRepository;

    public AnimalRepositoryImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }
    
    @Override
    public Optional<Animal> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Animal animal) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Animal> findByType(String type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Animal> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(Animal entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(Animal animal) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Animal> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTotalAnimalNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getAnimalNumberSortedByType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

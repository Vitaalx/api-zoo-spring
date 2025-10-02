package com.vitalx.apizoo.repositories;

import com.vitalx.apizoo.common.BaseRepository;
import com.vitalx.apizoo.entities.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends BaseRepository<Animal> {
    void update(Animal animal);

    List<Animal> findByType(final String type);

    void delete(final int id);

    void create(Animal animal);

    Optional<Animal> findByName(final String name);

    int getTotalAnimalNumber();

    int getAnimalNumberSortedByType();
}

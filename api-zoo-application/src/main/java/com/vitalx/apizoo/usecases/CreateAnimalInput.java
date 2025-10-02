package com.vitalx.apizoo.usecases;

import com.vitalx.apizoo.entities.AnimalType;

public record CreateAnimalInput(
        String name,
        int age,
        AnimalType type,
        String gender,
        String species,
        int weight,
        int height,
        int maxSpeed
) {
}

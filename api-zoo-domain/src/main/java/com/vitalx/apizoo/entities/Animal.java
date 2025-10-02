package com.vitalx.apizoo.entities;

import com.vitalx.apizoo.entities.common.AbstractEntity;
import com.vitalx.apizoo.usecases.CreateAnimalInput;

public class Animal extends AbstractEntity {
    private final String name;
    private final int age;
    private final AnimalType type;
    private final String gender;
    private final String species;
    private final int weight;
    private final int height;
    private final int maxSpeed;

    private Animal(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.type = builder.type;
        this.gender = builder.gender;
        this.species = builder.species;
        this.weight = builder.weight;
        this.height = builder.height;
        this.maxSpeed = builder.maxSpeed;
    }

    public static class Builder {
        private String name;
        private int age;
        private AnimalType type;
        private String gender;
        private String species;
        private int weight;
        private int height;
        private int maxSpeed;

        public Builder use(
                CreateAnimalInput createAnimalInput
        ) {
            this.name = createAnimalInput.name();
            this.age = createAnimalInput.age();
            this.type = createAnimalInput.type();
            this.gender = createAnimalInput.gender();
            this.species = createAnimalInput.species();
            this.weight = createAnimalInput.weight();
            this.height = createAnimalInput.height();
            this.maxSpeed = createAnimalInput.maxSpeed();

            return this;
        }

        public Animal build() {
            return new Animal(this);
        }
    }
}

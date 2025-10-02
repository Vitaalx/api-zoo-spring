package com.vitalx.apizoo.usecases;

import com.vitalx.apizoo.common.AbstractUseCase;
import com.vitalx.apizoo.common.UseCaseResult;
import com.vitalx.apizoo.entities.Animal;
import com.vitalx.apizoo.repositories.AnimalRepository;

public class CreateAnimalUseCase implements AbstractUseCase<
        CreateAnimalInput,
        Animal,
        String
        > {
    private final AnimalRepository animalRepository;

    public CreateAnimalUseCase(
            AnimalRepository animalRepository
    ) {
        this.animalRepository = animalRepository;
    }

    @Override
    public UseCaseResult<Animal, String> execute(CreateAnimalInput createAnimalInput) {
        Animal createdAnimal = new Animal.Builder()
                .use(createAnimalInput)
                .build();
        animalRepository.save(createdAnimal);
        return UseCaseResult.success(createdAnimal);
    }
}

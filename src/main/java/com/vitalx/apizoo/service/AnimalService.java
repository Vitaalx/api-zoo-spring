package com.vitalx.apizoo.service;

import com.vitalx.apizoo.dto.updateAnimalDTO;
import com.vitalx.apizoo.model.Animal;

import java.util.ArrayList;

public interface AnimalService {

    void initAnimals();
    Animal findById(final int id) ;
    boolean updateAnimal(int id, updateAnimalDTO a) throws Exception;
    String getDataFromRNAByText() throws Exception;
    ArrayList<Animal> getAnimalsByType(final AnimalsType type);
    ArrayList<Animal> getAnimals();
    boolean deleteAnimal(final int id);
    boolean createAnimal(ArrayList<Animal> animal);
    Animal getAnimalById(final int id);
    Animal getAnimalByName(final String name);
    int getNbAnimals();
    int getCountByType();

}

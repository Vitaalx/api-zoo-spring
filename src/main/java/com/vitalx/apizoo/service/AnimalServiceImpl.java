package com.vitalx.apizoo.service;

import com.vitalx.apizoo.dto.updateAnimalDTO;
import com.vitalx.apizoo.model.Animal;
import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@Data
@Service
public class AnimalServiceImpl implements AnimalService {

    private ArrayList<Animal> animals = new ArrayList<>();

    private static Logger logger = LoggerFactory.getLogger(AnimalServiceImpl.class);

    //Constructeur
    public AnimalServiceImpl() {
        super();
        initAnimals();
    }

    // Initialisation des animaux fictifs (BDD temporaire)
    public void initAnimals() {
        // Création animaux fictifs
        final Animal Milou = new Animal("Milou", 6, AnimalsType.Mammifères, "Chien", "Male");
        final Animal Garfield = new Animal("Garfield", 4, AnimalsType.Mammifères, "Chat", "Femelle");
        final Animal Mufasa = new Animal("Mufasa", 9, AnimalsType.Mammifères, "Lion", "Male");
        final Animal Oscar = new Animal("Oscar", 12, AnimalsType.Mammifères, "Singe", "Femelle");
        final Animal Maggie = new Animal("Maggie", 18, AnimalsType.Oiseaux, "Girafe", "Femelle");
        final Animal Alfi = new Animal("Alfi", 13, AnimalsType.Oiseaux, "Peroquet", "Male");
        final Animal Blume = new Animal("Blume", 7, AnimalsType.Poissons, "Le Black molly", "Femelle");
        final Animal Gali = new Animal("Gali", 22, AnimalsType.Poissons, "Néon bleu", "Femelle");
        final Animal Cronos = new Animal("Cronos", 16, AnimalsType.Poissons, "Le Platy", "Male");
        final Animal Rose = new Animal("Rose", 5, AnimalsType.Oiseaux, "Pelican", "Male");

        // Ajouts des animaux fictifs dans l'ArrayList
        animals.add(Milou);
        animals.add(Garfield);
        animals.add(Mufasa);
        animals.add(Oscar);
        animals.add(Maggie);
        animals.add(Alfi);
        animals.add(Blume);
        animals.add(Gali);
        animals.add(Cronos);
        animals.add(Rose);
    }

    /**
     * Use to search an Animal by his ID. (Function created to be more optimized)
     *
     * @param id ID of Animal
     * @return An Animal
     */
    public Animal findById(final int id) {
        Animal a = null;
        for (Animal animal : animals) {
            if (animal.getId() == id) {
                a = animal;
                break;
            }
        }
        return a;
    }

    /**
     * Use to update value of an Animal by ID.
     *
     * @param id ID use to identify the Animal to modify (int)
     * @param a  Value of the Animal to update
     * @return boolean (True of False)
     */
    public boolean updateAnimal(int id, updateAnimalDTO a) throws Exception {
        Animal animal = findById(id);
        EnumSet<AnimalsType> animalsTypeEnumSet = EnumSet.range(AnimalsType.Mammifères, AnimalsType.Poissons);

        if (findById(id) != null) {

            String sex = a.getSex();
            String nomEspece = a.getNomEspece();
            int age = a.getAge();

            if (sex != null && nomEspece != null && age != 0 && a.getType() != null) {

                AnimalsType type;
                try {
                    type = AnimalsType.valueOf(a.getType());

                } catch (IllegalArgumentException e) {
                    type = AnimalsType.Unknow;
                    logger.warn("@PUT - updateAnimal Invalid Type (" + a.getType() + ")" + " , must be one of those " + animalsTypeEnumSet + " initialized by Unknow.");
                }

                animal.setSex(sex);
                animal.setNomEspece(nomEspece);
                animal.setAge(age);
                animal.setType(type);
                return true;

            } else {
                logger.error("@PUT - updateAnimal All fields must be completed");
                return false;
            }
        } else {
            logger.error("@PUT - updateAnimal Not Found 404 Id : {}", id);
            return false;
        }
    }

    /**
     * Use to search Animal's by there type.
     *
     * @param type Value used to search Animal's
     * @return ArrayList<Animal>
     */
    public ArrayList<Animal> getAnimalsByType(final AnimalsType type) {
        ArrayList<Animal> filteredAnimals = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getType().equals(type)) {
                filteredAnimals.add(animal);
            }
        }
        if (filteredAnimals.isEmpty()) {
            logger.error("@GET - animalsByType Not Found 404 for type : {}", type);
            return new ArrayList<>();
        } else {
            return filteredAnimals;
        }
    }

    public String getDataFromRNAByText() throws IOException {
        StringBuilder source = new StringBuilder();
        URL oracle = new URL("https://entreprise.data.gouv.fr/api/rna/v1/full_text/LESPIEDSAUXMURS");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            source.append(inputLine);
        in.close();
        return source.toString();
    }

    /**
     * Use to get the nb of animal type's.
     *
     * @return An int
     */
    public int getCountByType() {
        int count = 0;
        EnumSet<AnimalsType> animalsTypeEnumSet = EnumSet.range(AnimalsType.Mammifères, AnimalsType.Poissons);
        for (AnimalsType i : animalsTypeEnumSet) {
            count++;
        }

        return count;
    }

    /**
     * Use to search an Animal by his ID.
     *
     * @param id ID of Animal
     * @return An Animal
     */
    public Animal getAnimalById(final int id) {
        Animal a = findById(id);
        if (a == null) {
            logger.error("@GET - animalsById Not Found 404 for Id : {}", id);
            return null;
        } else {
            return a;
        }
    }

    /**
     * Use to search an Animal by his Name
     *
     * @param name Name of the Animal to search
     * @return An Animal
     */
    public Animal getAnimalByName(final String name) {
        Animal a = null;
        for (Animal animal : animals) {
            if (Objects.equals(animal.getName(), name)) {
                a = animal;
                break;
            }
        }
        if (a == null) {
            logger.error("@GET - animalsByName Not Found 404 for name : {}", name);
            return null;
        } else {
            return a;
        }
    }

    /**
     * Use to display Animal's in the Zoo.
     *
     * @return Animal's
     */
    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    /**
     * Use to delete an Animal by his ID.
     *
     * @param id ID of the Animal to delete.
     * @return boolean (True of False)
     */
    public boolean deleteAnimal(final int id) {
        Animal animal = findById(id);
        if (animal != null) {
            this.animals.remove(animal);
            return true;
        } else {
            logger.error("@DELETE - deleteAnimal Not Found 404 Id : {}", id);
            return false;
        }
    }

    /**
     * Use to display number of Animal's in the Zoo.
     *
     * @return An int, number of Animal's in the Zoo.
     */
    public int getNbAnimals() {
            return animals.size();
    }

    /**
     * Use to check duplicate in my Zoo.
     *
     * @param animals An array with Animal's to create (for my use)
     * @param a       An animal
     * @return boolean (True of False)
     */
    static boolean contains(ArrayList<Animal> animals, Animal a) {
        for (Animal animal : animals) {
            if (a.getName().equals(animal.getName()))
                return true;
        }
        return false;
    }

    /**
     * Use to create some Animal's
     *
     * @param listAnimals An Array with Animal('s) to create.
     * @return boolean (true of False)
     */
    public boolean createAnimal(@NotNull ArrayList<Animal> listAnimals) {
        boolean isOk = false;
        for (Animal a : listAnimals) {
            if (!contains(animals, a)) {
                Animal lastElement = animals.get(animals.size() - 1);
                int lastId = lastElement.getId();
                a.setId(lastId + 1);
                animals.add(a);
                isOk = true;
            } else {
                logger.error("User unauthorized to create animal : {}", listAnimals);
                return false;
            }
        }
        if (!isOk) {
            logger.error("User unauthorized to create animal : {}", listAnimals);
            return false;
        }
        return true;
    }

}

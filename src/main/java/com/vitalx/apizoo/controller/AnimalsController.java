package com.vitalx.apizoo.controller;

import com.vitalx.apizoo.dto.updateAnimalDTO;
import com.vitalx.apizoo.model.Animal;
import com.vitalx.apizoo.service.AnimalService;

import com.vitalx.apizoo.service.AnimalsType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import javax.servlet.http.*;

@Api("API pour les opérations CRUD sur les Animaux (Gestion d'un Zoo).")

@RestController
@RequestMapping("/api-zoo")
public class AnimalsController extends HttpServlet {

    @Autowired
    private AnimalService animalsService;

    private static final Logger logger = LoggerFactory.getLogger(AnimalsController.class);

    /**
     * Create - Add a new animal
     * @param animal Some object's animal
     * @return A String with HttpStatus
     */
    @ApiOperation(value="Animal creation operation", nickname = "createAnimals", notes = "Create Animal(s) with JSON Object", response = String.class)
    @ApiResponses({
            @ApiResponse( code = 201, message = "Created", response = String.class, responseContainer = "String"),
            @ApiResponse( code = 401, message = "Unauthorized", response = String.class, responseContainer = "String")
    })
    @PostMapping("/createAnimals")
    public ResponseEntity<String> createAnimal(@RequestBody ArrayList<Animal> animal) {
        logger.info("Called createAnimal with animal : {}", animal);
        if(animalsService.createAnimal(animal)) {
            return new ResponseEntity<>("Animal créé", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Animal non créé. (USER_UNAUTHORIZED)", HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * Read - Get one or some animal
     * @param type The type of the animal
     * @return An Animals object full filled with HttpStatus
     */
    @ApiOperation(value="Specific animals type", nickname = "getAnimalsByType", notes = "Returns all Animals filter by type in path", response = Animal.class)
    @ApiResponses({
            @ApiResponse( code = 200, message = "Success", response = JSONObject.class, responseContainer = "JSObject"),
            @ApiResponse( code = 404, message = "Not Found", response = JSONObject.class, responseContainer = "JSObject")
    })
    @GetMapping("/animalsByType/{type}")
    public ResponseEntity<ArrayList<Animal>> getAnimalsByType(@PathVariable("type") final AnimalsType type) {
        logger.info("Called getAnimalsByType with type : {}", type);
        ArrayList<Animal> animal = animalsService.getAnimalsByType((type));
            if(animal.isEmpty()) {
                return new ResponseEntity<>(animal, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(animal, HttpStatus.OK);
            }
    }

    @GetMapping("/association/test")
    public ResponseEntity<String> getDataFromRNAByText() throws Exception {
        logger.info("Called getDataFromRNAByText");
        String data = animalsService.getDataFromRNAByText();
        if(data == null) {
            return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
    }

    /**
     * Read - Get all animals
     * @return - An Iterable object of Animals full filled with HttpStatus
     */
    @ApiOperation(value="Animals retrieving operation", nickname = "getAnimals", notes = "Returns all Animals", response = Animal.class)
    @ApiResponses({
            @ApiResponse( code = 200, message = "Success", response = JSONObject.class, responseContainer = "JSObject")
    })
    @GetMapping("/animals")
    public ResponseEntity<ArrayList<Animal>> getAnimals() {
        logger.info("Called getAnimals");
        return new ResponseEntity<>(animalsService.getAnimals(), HttpStatus.OK);
    }

    /**
     * Read - Get an animal by his ID
     * @return - An Animal with HttpStatus
     */
    @ApiOperation(value="Specific animal retrieving operation (byID)", nickname = "getAnimalById", notes = "Returns Animal identified by the Id in path", response = Animal.class)
    @ApiResponses({
            @ApiResponse( code = 200, message = "Success", response = JSONObject.class, responseContainer = "JSObject"),
            @ApiResponse( code = 404, message = "Not Found", response = JSONObject.class, responseContainer = "Null")
    })
    @GetMapping("/animalById/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable("id") final int id){
        logger.info("Called getAnimalById with id : {}", id);
        Animal animal = animalsService.getAnimalById(id);
        if(animal != null) {
            return new ResponseEntity<>(animal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Animal) null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Read - Get an animal by his Name
     * @return - An Animal with HttpStatus
     */
    @ApiOperation(value="Specific animal retrieving operation (byName)", nickname = "getAnimalByName", notes = "Returns Animal identified by the Name in path", response = Animal.class)
    @ApiResponses({
            @ApiResponse( code = 200, message = "Success", response = JSONObject.class, responseContainer = "JSObject"),
            @ApiResponse( code = 404, message = "Not Found", response = JSONObject.class, responseContainer = "Null")
    })
    @GetMapping("/animalByName/{name}")
    public ResponseEntity<Animal> getAnimalByName(@PathVariable("name") final String name) {
        logger.info("Called getAnimalByName with name : {}", name);
        Animal animal = animalsService.getAnimalByName(name);
        if(animal != null) {
            return new ResponseEntity<>(animal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Animal) null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Read - Get count of animals
     * @return - An int (nbAnimals) with HttpStatus
     */
    @ApiOperation(value="Count retrieving operation", nickname = "getNbAnimals", notes = "Returns number of Animals in the zoo", response = String.class)
    @ApiResponses({
            @ApiResponse( code = 200, message = "Success", response = String.class, responseContainer = "String")
    })
    @GetMapping("/animals/count")
    public ResponseEntity<String> getNbAnimals() {
        logger.info("Called getNbAnimals");
        return new ResponseEntity<>("Il y a " + animalsService.getNbAnimals() + " animaux dans le Zoo.", HttpStatus.OK);
    }

    /**
     * Read - Get count of animals by Type
     * @return - An int (nbAnimalsByType) with HttpStatus
     */
    @ApiOperation(value="Specific count retrieving operation (ByType)", nickname = "getNbAnimalsByType", notes = "Returns the number of animal types", response = String.class)
    @ApiResponses({
            @ApiResponse( code = 200, message = "Success", response = String.class, responseContainer = "String")
    })
    @GetMapping("/animals/countByType")
    public ResponseEntity<String> getNbAnimalsByType() {
        logger.info("Called getNbAnimalsByType");
        return new ResponseEntity<>("Il y a " + animalsService.getCountByType() + " types d'animaux.", HttpStatus.OK);
    }

    /**
     * Update - Update an existing animal
     * @param id - The id of the animal to update
     * @param animal - The animal object to update
     * @return A String with HttpStatus
     */
    @ApiOperation(value="Specific animal updating operation", nickname = "updateAnimal", notes = "Update Animal with ID.", response = String.class)
    @ApiResponses({
            @ApiResponse( code = 200, message = "Success", response = String.class, responseContainer = "String"),
            @ApiResponse( code = 400, message = "Bad Request", response = String.class, responseContainer = "String"),
            @ApiResponse( code = 404, message = "Not Found", response = String.class, responseContainer = "String")
    })
    @PutMapping("/animal/{id}")
    public ResponseEntity<String> updateAnimal(@PathVariable("id") final int id, @RequestBody updateAnimalDTO animal) throws Exception {
        logger.info("Called updateAnimal with animal : {}", animal);
        Optional<Animal> a = Optional.ofNullable(animalsService.findById(id));
        if (a.isPresent()) {
            if(animalsService.updateAnimal(id, animal)) {
                return new ResponseEntity<>("OK", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Tous les champs doivent être complétés ! (age, type, nomEspece et sex).", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Aucun animal n'est identifié par cet Id." + " Veuillez vérifier l'Id envoyé !" + "(ID: " + id + ").", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete - Delete an animal
     * @param id - The id of the animal to delete
     * @return boolean - (True of False) with HttpStatus
     */
    @ApiOperation(value="Specific animal deleting operation", nickname = "deleteAnimal", notes = "Delete Animal with ID.", response = String.class)
    @ApiResponses({
            @ApiResponse( code = 200, message = "Success", response = String.class, responseContainer = "String"),
            @ApiResponse( code = 404, message = "Not Found", response = String.class, responseContainer = "String")
    })
    @DeleteMapping("/animal/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable("id") final int id) {
        logger.info("Called deleteAnimal with id : {}", id);
        if(animalsService.deleteAnimal(id)) {
            // Ajouter Renvoie code http "Success" (200)
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Ajouter Renvoie code erreur "Not Found" (404)
            return new ResponseEntity<>("Aucun animal n'est identifié par cet Id." + " Veuillez vérifier l'Id envoyé !" + "(ID: " + id + ").", HttpStatus.NOT_FOUND);
        }
    }
}

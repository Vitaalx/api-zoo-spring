package com.vitalx.apizoo;

import com.vitalx.apizoo.dto.updateAnimalDTO;
import com.vitalx.apizoo.model.Animal;
import com.vitalx.apizoo.service.AnimalServiceImpl;
import com.vitalx.apizoo.service.AnimalsType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApiZooApplicationTests {

	@Autowired
	public AnimalServiceImpl animalService;

	/*

	--------------------------------------------------------------------------------
	|																			   |
	|						TEST : Service - getCountByType					       |
	|																			   |
	--------------------------------------------------------------------------------

	 */

	@Test
	public void countByTypeTest() {
		int countByType = animalService.getCountByType();

		assertEquals(3, countByType);
	}

	/*

	--------------------------------------------------------------------------------
	|																			   |
	|						TEST : Service - getNbAnimals					       |
	|																			   |
	--------------------------------------------------------------------------------

	 */

	/*  -----------------------------------
					Cas passant
	 	----------------------------------- */
	@Test
	public void nbOfAnimalsTest() {
		int nbOfAnimals = animalService.getNbAnimals();

		assertEquals(10, nbOfAnimals);
	}

	/*

	--------------------------------------------------------------------------------
	|																			   |
	|						TEST : Service - createAnimal					       |
	|																			   |
	--------------------------------------------------------------------------------

	 */

	/*  -----------------------------------
					Cas passant
	 	----------------------------------- */
	@Test
	public void createNewAnimalTest() {
		final Animal Liam = new Animal("Liam", 20, AnimalsType.Mammifères, "Homme", "Male");
		final ArrayList<Animal> listOfAnimals = new ArrayList<Animal>();
		int nbOfAnimals;

		listOfAnimals.add(Liam);
		animalService.createAnimal(listOfAnimals);
		nbOfAnimals = animalService.getNbAnimals();

		assertEquals(11, nbOfAnimals);
	}

	/*  -----------------------------------
		Cas non passant - Doublon on name
	 	----------------------------------- */
	@Test
	public void createNewAnimalWithNameDoublon() {
		// An animal as already the name : Cronos
		final Animal Liam = new Animal("Cronos", 20, AnimalsType.Mammifères, "Homme", "Male");
		final ArrayList<Animal> listOfAnimals = new ArrayList<Animal>();
		int nbOfAnimals;

		listOfAnimals.add(Liam);
		animalService.createAnimal(listOfAnimals);
		nbOfAnimals = animalService.getNbAnimals();

		assertEquals(11, nbOfAnimals);
	}

	/*

	--------------------------------------------------------------------------------
	|																			   |
	|						TEST : Service - deleteAnimal					       |
	|																			   |
	--------------------------------------------------------------------------------

	 */

	/*  -----------------------------------
					Cas passant
	 	----------------------------------- */
	@Test
	public void deleteAnAnimalTest() {
		int nbOfAnimals;
		int idToDelete = 3;

		animalService.deleteAnimal(idToDelete);
		nbOfAnimals = animalService.getNbAnimals();

		assertEquals(10, nbOfAnimals);
	}

	/*  -----------------------------------
		Cas non passant - Invalid id
	 	----------------------------------- */
	@Test
	public void deleteAnAnimalNotFoundCase() {
		int nbOfAnimals;
		// Not existing id : 284
		int idToDelete = 284;

		animalService.deleteAnimal(idToDelete);
		nbOfAnimals = animalService.getNbAnimals();

		// (Same number as the beginning : 11)
		assertEquals(10, nbOfAnimals);

	}

	/*

	--------------------------------------------------------------------------------
	|																			   |
	|						TEST : Service - getAnimals					   		   |
	|																			   |
	--------------------------------------------------------------------------------

	 */

	/*  -----------------------------------
				Cas passant
	 	----------------------------------- */
	@Test
	public void getAllAnimalsTest() {
		int nbOfAnimals = animalService.getNbAnimals();

		assertEquals(11, nbOfAnimals);
	}

	/*

	--------------------------------------------------------------------------------
	|																			   |
	|						TEST : Service - getAnimalByName					   |
	|																			   |
	--------------------------------------------------------------------------------

	 */

	/*  -----------------------------------
					Cas passant
	 	----------------------------------- */
	@Test
	public void getAnAnimalByNameTest() {
		final Animal expectedAnimal = new Animal("Alfi", 13, AnimalsType.Oiseaux, "Peroquet", "Male");
		Animal actualAnimal;

		actualAnimal = animalService.getAnimalByName("Alfi");

		assertEquals(expectedAnimal.getName(), actualAnimal.getName());
		assertEquals(expectedAnimal.getAge(), actualAnimal.getAge());
		assertEquals(expectedAnimal.getType(), actualAnimal.getType());
		assertEquals(expectedAnimal.getNomEspece(), actualAnimal.getNomEspece());
		assertEquals(expectedAnimal.getSex(), actualAnimal.getSex());
	}

	/*  -----------------------------------
		Cas non passant - Invalid name
	 	----------------------------------- */
	@Test
	public void getAnAnimalByNameNotFoundCase() {
		Animal actualAnimal;

		// Not existing Name : "Liam"
		actualAnimal = animalService.getAnimalByName("Alfred");

		assertNull(actualAnimal);
	}

	/*

	--------------------------------------------------------------------------------
	|																			   |
	|						TEST : Service - getAnimalByType				       |
	|																			   |
	--------------------------------------------------------------------------------

	 */

	/*  -----------------------------------
					Cas passant
	 	----------------------------------- */
	@Test
	public void getAnAnimalByTypeTest() {
		ArrayList<Animal> actualAnimal;

		actualAnimal = animalService.getAnimalsByType(AnimalsType.Mammifères);

		for(Animal a : actualAnimal) {
			assertEquals(AnimalsType.Mammifères, a.getType());
		}
	}

	/*  -----------------------------------
		Cas non passant - Invalid type
	 	----------------------------------- */
	@Test
	public void getAnAnimalByTypeNotFoundCase() {
		ArrayList<Animal> actualAnimal;
		ArrayList<Animal> emptyList = new ArrayList<>();

		actualAnimal = animalService.getAnimalsByType(AnimalsType.TEST);

		// Return an empty list if there isn't an animal with searched type.
		assertEquals(emptyList, actualAnimal);
	}

	/*

	--------------------------------------------------------------------------------
	|																			   |
	|						TEST : Service - getAnimalById						   |
	|																			   |
	--------------------------------------------------------------------------------

	 */

	/*  -----------------------------------
					Cas passant
	 	----------------------------------- */
	// TODO
	@Test
	public void getAnAnimalByIdTest() {
		/*String expectedNameOfAnimal = "Cronos";
		Animal actualAnimal = animalService.getAnimalById(8);

		assertEquals(expectedNameOfAnimal, actualAnimal.getName());*/
	}

	/*  -----------------------------------
		Cas non passant - Invalid Id
	 	----------------------------------- */
	@Test
	public void getAnimalByIdNotFoundCase() {
		Animal actualAnimal;

		// Not existing Id : 222
		actualAnimal = animalService.getAnimalById(222);

		assertNull(actualAnimal);
	}

	/*

	--------------------------------------------------------------------------------
	|																			   |
	|						TEST : Service - updateAnimal						   |
	|																			   |
	--------------------------------------------------------------------------------

	 */

	/*  -----------------------------------
					Cas passant
	 	----------------------------------- */
	@Test
	public void updateAnimalTest() throws Exception {
			String sexExpected = "Female";
			String nomEspeceExpected = "Le Plato";
			String typeExpected = "Oiseaux";
			int ageExpected = 19;
			Boolean isUpdated;

			updateAnimalDTO updateAnimalDTO = new updateAnimalDTO(ageExpected, typeExpected, sexExpected, nomEspeceExpected);
			// Actual Data - age: 16, type: Poissons, nomEspece: Le Platy, sex: Male
			Animal actualAnimal = animalService.getAnimalByName("Cronos");

			isUpdated = animalService.updateAnimal(actualAnimal.getId(), updateAnimalDTO);

			// Expected Data, after update - age: 19, type: Oiseaux, nomEspece: Le Plato, sex: Female
			assertEquals(sexExpected, actualAnimal.getSex());
			assertEquals(nomEspeceExpected, actualAnimal.getNomEspece());
			assertEquals(AnimalsType.valueOf(typeExpected), actualAnimal.getType());
			assertEquals(ageExpected, actualAnimal.getAge());
	}

	/*  -----------------------------------
		Cas non passant - Invalid type
	 	----------------------------------- */
	@Test
	public void updateAnimalTestWithInvalidType() throws Exception {
		String sexToChange = "Female";
		String nomEspeceToChange = "Le Plato";
		String typeToChange = "Reptile";
		int ageToChange = 19;
		Boolean isUpdated;

		updateAnimalDTO updateAnimalDTO = new updateAnimalDTO(ageToChange, typeToChange, sexToChange, nomEspeceToChange);
		// Actual Data - age: 16, type: Poissons, nomEspece: Le Platy, sex: Male
		Animal actualAnimal = animalService.getAnimalByName("Cronos");

		isUpdated = animalService.updateAnimal(actualAnimal.getId(), updateAnimalDTO);

		// Expected type on Animal : Unknow
		assertEquals(AnimalsType.Unknow, actualAnimal.getType());
	}

	/*  -----------------------------------
		Cas non passant - Missing parameters
	 	----------------------------------- */
	@Test
	public void updateAnimalTestWithMissingParameters() throws Exception {
		Boolean isUpdated;

		updateAnimalDTO updateAnimalDTO = new updateAnimalDTO(0, null, null, null);
		// Actual Data - age: 16, type: Poissons, nomEspece: Le Platy, sex: Male
		Animal actualAnimal = animalService.getAnimalByName("Cronos");

		isUpdated = animalService.updateAnimal(actualAnimal.getId(), updateAnimalDTO);

		Animal updatedAnimal = animalService.getAnimalByName("Cronos");

		// Expected no change on data
		assertEquals(updatedAnimal.getSex(), actualAnimal.getSex());
		assertEquals(updatedAnimal.getNomEspece(), actualAnimal.getNomEspece());
		assertEquals(updatedAnimal.getType(), actualAnimal.getType());
		assertEquals(updatedAnimal.getAge(), actualAnimal.getAge());
	}
}

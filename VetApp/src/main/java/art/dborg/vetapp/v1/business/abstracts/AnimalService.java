package art.dborg.vetapp.v1.business.abstracts;

import art.dborg.vetapp.v1.entities.Animal;

import java.util.List;

/**
 * This interface defines methods for managing animal entities.
 */
public interface AnimalService {
    /**
     * Saves a new animal entity.
     * @param animal The animal entity to save.
     * @return The saved animal entity.
     */
    Animal save(Animal animal);

    /**
     * Updates an existing animal entity.
     * @param animal The animal entity to update.
     * @return The updated animal entity.
     */
    Animal update(Animal animal);

    /**
     * Retrieves an animal entity by its ID.
     * @param id The ID of the animal to retrieve.
     * @return The retrieved animal entity.
     */
    Animal getId(long id);

    /**
     * Retrieves a list of animals associated with a specific customer ID.
     * @param animalCustomerId The ID of the customer.
     * @return A list of animal entities.
     */
    List<Animal> getCustomerId(long animalCustomerId);

    /**
     * Retrieves a list of animals by their name.
     * @param name The name of the animals to retrieve.
     * @return A list of animal entities.
     */
    List<Animal> getAnimalByName(String name);

    /**
     * Retrieves a list of animals associated with a specific customer name.
     * @param name The name of the customer.
     * @return A list of animal entities.
     */
    List<Animal> getCustomerName(String name);

    /**
     * Deletes an animal entity by its ID.
     * @param id The ID of the animal to delete.
     * @return A boolean indicating whether the deletion was successful.
     */
    boolean delete(long id);
}

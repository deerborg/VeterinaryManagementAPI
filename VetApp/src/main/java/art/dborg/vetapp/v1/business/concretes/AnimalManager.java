package art.dborg.vetapp.v1.business.concretes;

import art.dborg.vetapp.v1.business.abstracts.AnimalService;
import art.dborg.vetapp.v1.core.exception.ForUpdateNotFoundIdException;
import art.dborg.vetapp.v1.core.exception.NotFoundObjectRequest;
import art.dborg.vetapp.v1.core.exception.NotFoundCustomerException;
import art.dborg.vetapp.v1.core.exception.NotFoundException;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.dao.CustomerRepository;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.dao.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * This class implements the AnimalService interface and provides methods for managing animal entities.
 */
@Service
@RequiredArgsConstructor
public class AnimalManager implements AnimalService {
    private final AnimalRepository animalRepository;
    private final CustomerRepository customerRepository;

    /**
     * Saves a new animal entity.
     * @param animal The animal entity to be saved.
     * @return The saved animal entity.
     * @throws NotFoundCustomerException If the associated customer is not found.
     */
    @Override
    public Animal save(Animal animal) {
        if (customerRepository.findById(animal.getCustomer().getId()).isEmpty()) {
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
        return animalRepository.save(animal);
    }

    /**
     * Updates an existing animal entity.
     * @param animal The animal entity to be updated.
     * @return The updated animal entity.
     * @throws ForUpdateNotFoundIdException If the animal to be updated is not found.
     * @throws NotFoundCustomerException If the associated customer is not found.
     */
    @Override
    public Animal update(Animal animal) {
        animalRepository.findById(animal.getId()).orElseThrow(()-> new ForUpdateNotFoundIdException(Message.UPDATE_NOT_FOUND_ID));
        if (customerRepository.findById(animal.getCustomer().getId()).isEmpty()) {
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
        return animalRepository.save(animal);
    }

    /**
     * Retrieves an animal entity by its ID.
     * @param id The ID of the animal to retrieve.
     * @return The retrieved animal entity.
     * @throws NotFoundException If the animal with the specified ID is not found.
     */
    @Override
    public Animal getId(long id) {
        return animalRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID));
    }

    /**
     * Retrieves a list of animal entities associated with a specific customer ID.
     * @param animalCustomerId The ID of the customer whose animals are to be retrieved.
     * @return The list of animal entities associated with the specified customer ID.
     * @throws NotFoundCustomerException If no animals are found for the specified customer ID.
     */
    @Override
    public List<Animal> getCustomerId(long animalCustomerId) {
        if(animalRepository.findByCustomer_Id(animalCustomerId).isEmpty()){
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
        return animalRepository.findByCustomer_Id(animalCustomerId);
    }

    /**
     * Retrieves a list of animal entities with the specified name.
     * @param name The name of the animal(s) to retrieve.
     * @return The list of animal entities with the specified name.
     * @throws NotFoundObjectRequest If no animals are found with the specified name.
     */
    @Override
    public List<Animal> getAnimalByName(String name) {
        if(animalRepository.findByName(name).isEmpty()){
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return animalRepository.findByName(name);
    }

    /**
     * Retrieves a list of animal entities associated with a customer having the specified name.
     * @param name The name of the customer whose animals are to be retrieved.
     * @return The list of animal entities associated with the customer having the specified name.
     * @throws NotFoundObjectRequest If no animals are found for the customer with the specified name.
     */
    @Override
    public List<Animal> getCustomerName(String name) {
        if(animalRepository.findByCustomerName(name).isEmpty()){
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return animalRepository.findByCustomerName(name);
    }

    /**
     * Deletes an animal entity by its ID.
     * @param id The ID of the animal to delete.
     * @return A boolean indicating whether the deletion was successful.
     */
    @Override
    public boolean delete(long id) {
        animalRepository.delete(getId(id));
        return true;
    }
}

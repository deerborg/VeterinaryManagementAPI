package art.dborg.vetapp.v1.dao;

import art.dborg.vetapp.v1.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface provides methods for accessing animal data in the database.
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    /**
     * Finds animals by customer ID.
     * @param animalCustomerId The ID of the customer.
     * @return A list of animals belonging to the specified customer.
     */
    List<Animal> findByCustomer_Id(long animalCustomerId);

    /**
     * Finds animals by name.
     * @param name The name of the animal.
     * @return A list of animals with the specified name.
     */
    List<Animal> findByName(String name);

    /**
     * Finds animals by customer name.
     * @param name The name of the customer.
     * @return A list of animals belonging to customers with the specified name.
     */
    List<Animal> findByCustomerName(String name);
}

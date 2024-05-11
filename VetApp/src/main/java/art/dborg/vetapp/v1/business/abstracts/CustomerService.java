package art.dborg.vetapp.v1.business.abstracts;

import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Customer;

import java.util.List;

/**
 * This interface defines methods for managing customer entities.
 */
public interface CustomerService {
    /**
     * Saves a new customer.
     * @param customer The customer to save.
     * @return The saved customer.
     */
    Customer save(Customer customer);

    /**
     * Updates an existing customer.
     * @param customer The customer to update.
     * @return The updated customer.
     */
    Customer update(Customer customer);

    /**
     * Retrieves a customer by its ID.
     * @param id The ID of the customer to retrieve.
     * @return The retrieved customer.
     */
    Customer getId(long id);

    /**
     * Deletes a customer by its ID.
     * @param id The ID of the customer to delete.
     * @return A boolean indicating whether the deletion was successful.
     */
    boolean delete(long id);

    /**
     * Retrieves customers by their name.
     * @param name The name of the customers to retrieve.
     * @return A list of customers with the specified name.
     */
    List<Customer> getByCustomerName(String name);

    /**
     * Retrieves animals associated with a customer.
     * @param id The ID of the customer whose animals to retrieve.
     * @return A list of animals associated with the specified customer.
     */
    List<Animal> getByAnimalList(long id);
}

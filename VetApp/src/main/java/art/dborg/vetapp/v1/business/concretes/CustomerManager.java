package art.dborg.vetapp.v1.business.concretes;

import art.dborg.vetapp.v1.business.abstracts.CustomerService;
import art.dborg.vetapp.v1.core.exception.*;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.dao.CustomerRepository;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class implements the CustomerService interface and provides methods for managing customer entities.
 */
@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;

    /**
     * Saves a new customer.
     * @param customer The customer entity to be saved.
     * @return The saved customer entity.
     * @throws NotUniqueValues If the provided email or phone number already exist in the database.
     */
    @Override
    public Customer save(Customer customer) {
        if(customerRepository.existsByMail(customer.getMail()) || customerRepository.existsByPhone(customer.getPhone())){
            throw new NotUniqueValues(Message.NOT_UNIQUE);
        }
        return customerRepository.save(customer);
    }

    /**
     * Updates an existing customer.
     * @param customer The customer entity to be updated.
     * @return The updated customer entity.
     * @throws ForUpdateNotFoundIdException If the customer to be updated is not found.
     */
    @Override
    public Customer update(Customer customer) {
        customerRepository.findById(customer.getId()).orElseThrow(()-> new ForUpdateNotFoundIdException(Message.UPDATE_NOT_FOUND_ID));
        return customerRepository.save(customer);
    }

    /**
     * Retrieves a customer entity by its ID.
     * @param id The ID of the customer to retrieve.
     * @return The retrieved customer entity.
     * @throws NotFoundException If the customer with the specified ID is not found.
     */
    @Override
    public Customer getId(long id) {
        return customerRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID));
    }

    /**
     * Deletes a customer entity by its ID.
     * @param id The ID of the customer to delete.
     * @return A boolean indicating whether the deletion was successful.
     */
    @Override
    public boolean delete(long id) {
        customerRepository.delete(getId(id));
        return true;
    }

    /**
     * Retrieves a list of customers by their name.
     * @param name The name of the customers to retrieve.
     * @return A list of customers with the specified name.
     * @throws NotFoundObjectRequest If no customers are found with the specified name.
     */
    @Override
    public List<Customer> getByCustomerName(String name) {
        if(customerRepository.findByName(name).isEmpty()){
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return customerRepository.findByName(name);
    }

    /**
     * Retrieves a list of animals belonging to a customer.
     * @param id The ID of the customer.
     * @return A list of animals belonging to the customer with the specified ID.
     * @throws NotFoundCustomerException If the customer with the specified ID is not found.
     * @throws NotFoundObjectRequest If no animals are found for the customer.
     */
    @Override
    public List<Animal> getByAnimalList(long id) {
        if(customerRepository.findById(id).isEmpty()){
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
        if(getId(id).getAnimalList().isEmpty()){
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return getId(id).getAnimalList();
    }
}

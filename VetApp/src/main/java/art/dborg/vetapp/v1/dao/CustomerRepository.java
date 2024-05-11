package art.dborg.vetapp.v1.dao;

import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface provides methods for accessing customer data in the database.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * Checks if a customer exists with the specified phone number.
     * @param phone The phone number to check.
     * @return True if a customer exists with the specified phone number, false otherwise.
     */
    boolean existsByPhone(String phone);

    /**
     * Checks if a customer exists with the specified email.
     * @param mail The email to check.
     * @return True if a customer exists with the specified email, false otherwise.
     */
    boolean existsByMail(String mail);

    /**
     * Finds customers by their name.
     * @param name The name of the customers to find.
     * @return A list of customers with the specified name.
     */
    List<Customer> findByName(String name);
}

package art.dborg.vetapp.petcare.repository;


import art.dborg.vetapp.petcare.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByPhone(String phone);
    boolean existsByMail(String mail);
    List<Customer> findByName(String name);
    boolean deleteByName(String name);
    Customer findCustomerByName(String name);
}

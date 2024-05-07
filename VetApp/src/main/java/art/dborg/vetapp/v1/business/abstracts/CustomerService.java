package art.dborg.vetapp.v1.business.abstracts;

import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    Customer update(Customer customer);
    Customer getId(long id);
    boolean delete(long id);
}

package art.dborg.vetapp.v1.business.concretes;

import art.dborg.vetapp.v1.business.abstracts.CustomerService;
import art.dborg.vetapp.v1.dao.CustomerRepository;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public Customer getId(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

}

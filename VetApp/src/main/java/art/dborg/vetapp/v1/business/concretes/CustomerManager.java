package art.dborg.vetapp.v1.business.concretes;

import art.dborg.vetapp.v1.business.abstracts.CustomerService;
import art.dborg.vetapp.v1.core.exception.ForUpdateNotFoundIdException;
import art.dborg.vetapp.v1.core.exception.NotFoundException;
import art.dborg.vetapp.v1.core.exception.NotUniqueValues;
import art.dborg.vetapp.v1.core.utilities.Message;
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
        if(customerRepository.existsByMail(customer.getMail()) || customerRepository.existsByPhone(customer.getPhone())){
            throw new NotUniqueValues(Message.NOT_UNIQ);
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        customerRepository.findById(customer.getId()).orElseThrow(()-> new ForUpdateNotFoundIdException(Message.UPDATE_NOT_FOUND_ID));
        if(customerRepository.existsByMail(customer.getMail()) || customerRepository.existsByPhone(customer.getPhone())){
            throw new NotUniqueValues(Message.NOT_UNIQ);
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer getId(long id) {
        return customerRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID));
    }

    @Override
    public boolean delete(long id) {
        customerRepository.delete(getId(id));
        return true;
    }

}

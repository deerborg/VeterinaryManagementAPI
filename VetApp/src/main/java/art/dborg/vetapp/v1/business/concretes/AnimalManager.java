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

@Service
@RequiredArgsConstructor
public class AnimalManager implements AnimalService {
    private final AnimalRepository animalRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Animal save(Animal animal) {
        if (customerRepository.findById(animal.getCustomer().getId()).isEmpty()) {
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
        return animalRepository.save(animal);
    }

    @Override
    public Animal update(Animal animal) {
        animalRepository.findById(animal.getId()).orElseThrow(()-> new ForUpdateNotFoundIdException(Message.UPDATE_NOT_FOUND_ID));
        if (customerRepository.findById(animal.getCustomer().getId()).isEmpty()) {
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
        return animalRepository.save(animal);
    }

    @Override
    public Animal getId(long id) {
        return animalRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID));
    }

    @Override
    public List<Animal> getCustomerId(long animalCustomerId) {
        if(animalRepository.findByCustomer_Id(animalCustomerId).isEmpty()){
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
        return animalRepository.findByCustomer_Id(animalCustomerId);
    }

    @Override
    public List<Animal> getAnimalByName(String name) {
        if(animalRepository.findByName(name).isEmpty()){
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return animalRepository.findByName(name);
    }

    @Override
    public boolean delete(long id) {
        animalRepository.delete(getId(id));
        return true;
    }


}

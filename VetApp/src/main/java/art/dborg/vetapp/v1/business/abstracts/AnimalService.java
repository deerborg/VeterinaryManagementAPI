package art.dborg.vetapp.v1.business.abstracts;

import art.dborg.vetapp.v1.entities.Animal;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnimalService {
    Animal save(Animal animal);
    Animal update(Animal animal);
    Animal getId(long id);
    List<Animal> getCustomerId(long animalCustomerId);
    List<Animal> getAnimalByName(String name);
    boolean delete(long id);
}

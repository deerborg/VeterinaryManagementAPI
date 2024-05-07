package art.dborg.vetapp.v1.business.concretes;

import art.dborg.vetapp.v1.business.abstracts.VaccineService;
import art.dborg.vetapp.v1.core.exception.NotFoundAnimalException;
import art.dborg.vetapp.v1.core.exception.NotFoundException;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.dao.AnimalRepository;
import art.dborg.vetapp.v1.dao.VaccineRepository;
import art.dborg.vetapp.v1.entities.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaccineManager implements VaccineService {
    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;
    @Override
    public Vaccine addVaccine(Vaccine vaccine) {
        if(animalRepository.findById(vaccine.getAnimal().getId()).isEmpty()){
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        return vaccineRepository.save(vaccine);
    }

    @Override
    public Vaccine getId(long id) {
        return vaccineRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID));
    }
}

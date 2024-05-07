package art.dborg.vetapp.v1.business.concretes;

import art.dborg.vetapp.v1.business.abstracts.VaccineService;
import art.dborg.vetapp.v1.core.exception.*;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.dao.AnimalRepository;
import art.dborg.vetapp.v1.dao.VaccineRepository;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccineManager implements VaccineService {
    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;

    @Override
    public Vaccine addVaccine(Vaccine vaccine) {
        if (animalRepository.findById(vaccine.getAnimal().getId()).isEmpty()) {
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        if (vaccineRepository.existsVaccineByCodeAndName(vaccine.getCode(),vaccine.getName())) {
            int dateCalForNewVaccine = vaccine.getStartDate().compareTo(vaccineRepository.findById(vaccine.getId()).orElseThrow(()-> new ForUpdateNotFoundId(Message.UPDATE_NOT_FOUND_ID)).getEndDate());
            if(dateCalForNewVaccine < 0){
                throw new DateMistmatchException(Message.DATE_MISMATCH);
            }
            int badDateCheck = vaccine.getEndDate().compareTo(vaccine.getStartDate());
            if(badDateCheck < 0){
                throw new NoneSenseInformationException(Message.BAD_DATE);
            }
            return vaccineRepository.save(vaccine);
        }
        return vaccineRepository.save(vaccine);
    }

    @Override
    public Vaccine getId(long id) {
        return vaccineRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID));
    }
}

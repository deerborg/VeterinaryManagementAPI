package art.dborg.vetapp.v1.business.concretes;

import art.dborg.vetapp.v1.business.abstracts.VaccineService;
import art.dborg.vetapp.v1.core.exception.*;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.dao.AnimalRepository;
import art.dborg.vetapp.v1.dao.VaccineRepository;
import art.dborg.vetapp.v1.entities.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * This class implements the VaccineService interface and provides methods for managing vaccine entities.
 */
@Service
@RequiredArgsConstructor
public class VaccineManager implements VaccineService {
    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;

    /**
     * Adds a new vaccine.
     * @param vaccine The vaccine entity to be added.
     * @return The added vaccine entity.
     * @throws NotFoundAnimalException If the animal ID associated with the vaccine is not found.
     * @throws DateMistmatchException If there is a vaccine that still provides protection.
     * @throws NoneSenseInformationException If the end date is before the start date or other senseless information is provided.
     */
    @Override
    public Vaccine addVaccine(Vaccine vaccine) {
        if (animalRepository.findById(vaccine.getAnimal().getId()).isEmpty()) {
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        if (vaccineRepository.existsVaccineByCodeAndNameAndAnimal_id(vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId())) {
            for (int i = 0; i < vaccineRepository.findAll().size(); i++) {
                if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccineRepository.findAll().get(i).getEndDate()) >= 0) {
                    throw new DateMistmatchException(Message.DATE_MISMATCH);
                }
                if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getEndDate()) < 0) {
                    throw new NoneSenseInformationException(Message.BAD_DATE);
                }
            }
            return vaccineRepository.save(vaccine);
        }
        if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getEndDate()) < 0) {
            throw new NoneSenseInformationException(Message.BAD_DATE);
        }
        return vaccineRepository.save(vaccine);
    }

    /**
     * Retrieves a vaccine entity by its ID.
     * @param id The ID of the vaccine to retrieve.
     * @return The retrieved vaccine entity.
     * @throws NotFoundException If the vaccine with the specified ID is not found.
     */
    @Override
    public Vaccine getId(long id) {
        return vaccineRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID));
    }

    /**
     * Updates an existing vaccine.
     * @param vaccine The vaccine entity to be updated.
     * @return The updated vaccine entity.
     * @throws NotFoundException If the vaccine to be updated is not found.
     * @throws DateMistmatchException If there is a vaccine that still provides protection.
     * @throws NoneSenseInformationException If the end date is before the start date or other senseless information is provided.
     */
    @Override
    public Vaccine update(Vaccine vaccine) {
        getId(vaccine.getId());
        if(vaccineRepository.countVaccineByNameAndCodeAndAnimal_Id(vaccine.getName(),vaccine.getCode(),vaccine.getAnimal().getId()) >= 2){
            throw new ForceUpdateException(Message.FORCE_UPDATE);
        }
        return addVaccine(vaccine);
    }

    /**
     * Updates an existing vaccine forcefully.
     * @param vaccine The vaccine entity to be updated.
     * @return The updated vaccine entity.
     * @throws NotFoundException If the vaccine to be updated is not found.
     * @throws NoneSenseInformationException If the end date is before the start date or other senseless information is provided.
     */
    @Override
    public Vaccine forceUpdate(Vaccine vaccine) {
        getId(vaccine.getId());
        if (vaccine.getEndDate().isBefore(vaccine.getStartDate())) {
            throw new NoneSenseInformationException(Message.BAD_DATE);
        }
        return vaccineRepository.save(vaccine);
    }

    /**
     * Deletes a vaccine entity by its ID.
     * @param id The ID of the vaccine to delete.
     * @return A boolean indicating whether the deletion was successful.
     */
    @Override
    public boolean delete(long id) {
        vaccineRepository.delete(getId(id));
        return true;
    }

    /**
     * Retrieves a list of vaccine entities associated with a specific animal.
     * @param id The ID of the animal.
     * @return A list of vaccine entities.
     * @throws NotFoundAnimalException If the animal with the specified ID is not found.
     */
    @Override
    public List<Vaccine> getAnimalVaccineList(long id) {
        if (vaccineRepository.findByAnimal_Id(id).isEmpty()) {
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        return vaccineRepository.findByAnimal_Id(id);
    }

    /**
     * Retrieves a list of vaccine entities filtered by end date.
     * @param endDate The end date to filter by.
     * @return A list of vaccine entities.
     * @throws NotFoundObjectRequest If no vaccine entities are found with the specified end date.
     */
    @Override
    public List<Vaccine> getFilterByStartAndEndDate(LocalDate firstDate,LocalDate endDate) {
        if (vaccineRepository.findByEndDateBetween(firstDate,endDate).isEmpty()) {
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return vaccineRepository.findByEndDateBetween(firstDate,endDate);
    }

}

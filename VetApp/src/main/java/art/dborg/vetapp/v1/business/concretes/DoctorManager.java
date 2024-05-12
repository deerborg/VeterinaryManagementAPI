package art.dborg.vetapp.v1.business.concretes;

import art.dborg.vetapp.v1.business.abstracts.DoctorService;
import art.dborg.vetapp.v1.core.exception.ForUpdateNotFoundIdException;
import art.dborg.vetapp.v1.core.exception.NotFoundException;
import art.dborg.vetapp.v1.core.exception.NotUniqueValues;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.dao.DoctorRepository;
import art.dborg.vetapp.v1.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * This class implements the DoctorService interface and provides methods for managing doctor entities.
 */
@RequiredArgsConstructor
@Service
public class DoctorManager implements DoctorService {

    private final DoctorRepository doctorRepository;

    /**
     * Saves a new doctor.
     * @param doctor The doctor entity to be saved.
     * @return The saved doctor entity.
     * @throws NotUniqueValues If the provided email or phone number already exist in the database.
     */
    @Override
    public Doctor save(Doctor doctor) { // Section 15 - Registering a doctor
        if(doctorRepository.existsByMailOrPhone(doctor.getMail(),doctor.getPhone())){
            throw new NotUniqueValues(Message.NOT_UNIQUE);
        }
        return doctorRepository.save(doctor);
    }

    /**
     * Updates an existing doctor.
     * @param doctor The doctor entity to be updated.
     * @return The updated doctor entity.
     * @throws ForUpdateNotFoundIdException If the doctor to be updated is not found.
     */
    @Override
    public Doctor update(Doctor doctor) {
        doctorRepository.findById(doctor.getId()).orElseThrow(()-> new ForUpdateNotFoundIdException(Message.UPDATE_NOT_FOUND_ID));
        return doctorRepository.save(doctor);
    }

    /**
     * Retrieves a doctor entity by its ID.
     * @param id The ID of the doctor to retrieve.
     * @return The retrieved doctor entity.
     * @throws NotFoundException If the doctor with the specified ID is not found.
     */
    @Override
    public Doctor getId(long id) {
        return doctorRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID));
    }

    /**
     * Deletes a doctor entity by its ID.
     * @param id The ID of the doctor to delete.
     * @return A boolean indicating whether the deletion was successful.
     */
    @Override
    public boolean delete(long id) {
        doctorRepository.delete(getId(id));
        return true;
    }
}

package art.dborg.vetapp.v1.business.concretes;

import art.dborg.vetapp.v1.business.abstracts.AvailableDateService;
import art.dborg.vetapp.v1.core.exception.AppointmentAlreadyExists;
import art.dborg.vetapp.v1.core.exception.NotFoundDoctorException;
import art.dborg.vetapp.v1.core.exception.NotFoundException;
import art.dborg.vetapp.v1.core.exception.SameValuesException;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.dao.AppointmentRepository;
import art.dborg.vetapp.v1.dao.AvailableRepository;
import art.dborg.vetapp.v1.dao.DoctorRepository;
import art.dborg.vetapp.v1.entities.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * This class implements the AvailableDateService interface and provides methods for managing available date entities.
 */
@Service
@RequiredArgsConstructor
public class AvailableDateManager implements AvailableDateService {
    private final AvailableRepository availableRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    /**
     * Saves a new available date.
     * @param availableDate The available date entity to be saved.
     * @return The saved available date entity.
     * @throws NotFoundDoctorException If the associated doctor is not found.
     * @throws SameValuesException If the same available date already exists for the specified doctor.
     */
    @Override
    public AvailableDate save(AvailableDate availableDate) {
        if(doctorRepository.findById(availableDate.getDoctors().getId()).isEmpty()){
            throw new NotFoundDoctorException(Message.NOT_FOUND_DOCTOR);
        }
        if(availableRepository.existsByDateAndDoctors_Id(availableDate.getDate(),availableDate.getDoctors().getId())){
            throw new SameValuesException(Message.SAME_VALUES);
        }
        return availableRepository.save(availableDate);
    }

    /**
     * Updates an existing available date.
     * @param availableDate The available date entity to be updated.
     * @return The updated available date entity.
     * @throws NotFoundException If the available date to be updated is not found.
     * @throws NotFoundDoctorException If the associated doctor is not found.
     * @throws SameValuesException If the same available date already exists for the specified doctor.
     * @throws AppointmentAlreadyExists If appointments exist for the specified available date.
     */
    @Override
    public AvailableDate update(AvailableDate availableDate) {
        if(appointmentRepository.existsByAvailableDate_Id(availableDate.getId())){
            throw new AppointmentAlreadyExists(Message.EXISTING_APPOINTMENT);
        }
        availableRepository.findById(availableDate.getId()).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID));
        if(doctorRepository.findById(availableDate.getDoctors().getId()).isEmpty()){
            throw new NotFoundDoctorException(Message.NOT_FOUND_DOCTOR);
        }
        if(availableRepository.existsByDateAndDoctors_Id(availableDate.getDate(),availableDate.getDoctors().getId())){
            throw new SameValuesException(Message.SAME_VALUES);
        }
        return availableRepository.save(availableDate);
    }

    /**
     * Retrieves an available date entity by its ID.
     * @param id The ID of the available date to retrieve.
     * @return The retrieved available date entity.
     * @throws NotFoundException If the available date with the specified ID is not found.
     */
    @Override
    public AvailableDate getId(long id) {
        return availableRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID));
    }

    /**
     * Deletes an available date entity by its ID.
     * @param id The ID of the available date to delete.
     * @return A boolean indicating whether the deletion was successful.
     * @throws AppointmentAlreadyExists If appointments exist for the specified available date.
     */
    @Override
    public boolean delete(long id) {
        if(appointmentRepository.existsByAvailableDate_Id(id)){
            throw new AppointmentAlreadyExists(Message.EXISTING_APPOINTMENT);
        }
        availableRepository.delete(getId(id));
        return true;
    }
}

package art.dborg.vetapp.v1.business.concretes;

import art.dborg.vetapp.v1.business.abstracts.AppointmentService;
import art.dborg.vetapp.v1.core.exception.*;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.dao.AnimalRepository;
import art.dborg.vetapp.v1.dao.AppointmentRepository;
import art.dborg.vetapp.v1.dao.AvailableRepository;
import art.dborg.vetapp.v1.dao.DoctorRepository;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Appointment;
import art.dborg.vetapp.v1.entities.AvailableDate;
import art.dborg.vetapp.v1.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This class implements the AppointmentService interface and provides methods for managing appointment entities.
 */
@Service
@RequiredArgsConstructor
public class AppointmentManager implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final AvailableRepository availableRepository;
    private final AnimalRepository animalRepository;

    /**
     * Adds a new appointment.
     * @param appointment The appointment entity to be added.
     * @return The added appointment entity.
     * @throws NotFoundException If the associated animal or doctor is not found.
     * @throws DoctorDaysConflictException If the doctor is not available on the specified date.
     * @throws AppointmentConflictException If there is a conflict with an existing appointment at the same time.
     */
    @Override
    public Appointment addAppointments(Appointment appointment) {
        if (animalRepository.findById(appointment.getAnimal().getId()).isEmpty() || doctorRepository.findById(appointment.getDoctor().getId()).isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND_ID);
        }
        if (availableRepository.existsByDateAndDoctors_Id(appointment.getDateTime().toLocalDate(), appointment.getDoctor().getId())) {
            appointment.setAvailableDate(availableRepository.findByDate(appointment.getDateTime().toLocalDate()));
            for (int i = 0; i < appointmentRepository.findAll().size(); i++) {
                if(appointmentRepository.existsByDoctor_Id(appointment.getDoctor().getId())){
                    if(Duration.between(appointment.getDateTime(),appointmentRepository.findAll().get(i).getDateTime()).toHours() == 0){
                        throw new AppointmentConflictException(Message.APPOINTMENT_CONFLICT);
                    }
                }
            }
            return appointmentRepository.save(appointment);
        }
        throw new DoctorDaysConflictException(Message.DAYS_CONFLICT);
    }

    /**
     * Updates an existing appointment.
     * @param appointment The appointment entity to be updated.
     * @return The updated appointment entity.
     * @throws NotFoundException If the appointment to be updated is not found.
     * @throws DoctorDaysConflictException If the doctor is not available on the specified date.
     * @throws AppointmentConflictException If there is a conflict with an existing appointment at the same time.
     */
    @Override
    public Appointment update(Appointment appointment) {
        getId(appointment.getId());
        return addAppointments(appointment);
    }

    /**
     * Retrieves an appointment entity by its ID.
     * @param id The ID of the appointment to retrieve.
     * @return The retrieved appointment entity.
     * @throws NotFoundException If the appointment with the specified ID is not found.
     */
    @Override
    public Appointment getId(long id) {
        return appointmentRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID));
    }

    /**
     * Deletes an appointment entity by its ID.
     * @param id The ID of the appointment to delete.
     * @return A boolean indicating whether the deletion was successful.
     */
    @Override
    public boolean delete(long id) {
        appointmentRepository.delete(getId(id));
        return true;
    }

    /**
     * Filters appointment entities by date and doctor.
     * @param startDate The start date of the filter range.
     * @param endDate The end date of the filter range.
     * @param doctor The doctor associated with the appointments.
     * @return The list of filtered appointment entities.
     * @throws NotFoundDoctorException If the specified doctor is not found.
     * @throws NotFoundAppointment If no appointments are found within the specified date range.
     */
    @Override
    public List<Appointment> filterDateTimeAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor) {
        if(doctorRepository.findById(doctor.getId()).isEmpty()){
            throw new NotFoundDoctorException(Message.NOT_FOUND_DOCTOR);
        }
        if(!appointmentRepository.existsByDateTimeBetween(startDate,endDate)){
            throw new NotFoundAppointment(Message.NOT_FOUND_APPOINTMENT);
        }
        return appointmentRepository.findByDateTimeBetweenAndDoctor(startDate,endDate,doctor);
    }

    /**
     * Filters appointment entities by date and animal.
     * @param startDate The start date of the filter range.
     * @param endDate The end date of the filter range.
     * @param animal The animal associated with the appointments.
     * @return The list of filtered appointment entities.
     * @throws NotFoundAnimalException If the specified animal is not found.
     * @throws NotFoundAppointment If no appointments are found within the specified date range.
     */
    @Override
    public List<Appointment> filterDateTimeAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal) {
        if(animalRepository.findById(animal.getId()).isEmpty()){
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        if(!appointmentRepository.existsByDateTimeBetween(startDate,endDate)){
            throw new NotFoundAppointment(Message.NOT_FOUND_APPOINTMENT);
        }
        return appointmentRepository.findByDateTimeBetweenAndAnimal(startDate,endDate,animal);
    }
}

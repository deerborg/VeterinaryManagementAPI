package art.dborg.vetapp.v1.business.abstracts;

import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Appointment;
import art.dborg.vetapp.v1.entities.Doctor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This interface defines methods for managing appointment entities.
 */
public interface AppointmentService {
    /**
     * Adds a new appointment.
     * @param appointment The appointment to add.
     * @return The added appointment.
     */
    Appointment addAppointments(Appointment appointment);

    /**
     * Updates an existing appointment.
     * @param appointment The appointment to update.
     * @return The updated appointment.
     */
    Appointment update(Appointment appointment);

    /**
     * Retrieves an appointment by its ID.
     * @param id The ID of the appointment to retrieve.
     * @return The retrieved appointment.
     */
    Appointment getId(long id);

    /**
     * Filters appointments based on date and doctor.
     * @param startDate The start date of the filter.
     * @param endDate The end date of the filter.
     * @param doctor The doctor to filter appointments for.
     * @return A list of filtered appointments.
     */
    List<Appointment> filterDateTimeAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor);

    /**
     * Filters appointments based on date and animal.
     * @param startDate The start date of the filter.
     * @param endDate The end date of the filter.
     * @param animal The animal to filter appointments for.
     * @return A list of filtered appointments.
     */
    List<Appointment> filterDateTimeAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal);

    /**
     * Deletes an appointment by its ID.
     * @param id The ID of the appointment to delete.
     * @return A boolean indicating whether the deletion was successful.
     */
    boolean delete(long id);
}

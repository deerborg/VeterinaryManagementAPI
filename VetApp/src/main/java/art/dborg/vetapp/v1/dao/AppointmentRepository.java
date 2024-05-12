package art.dborg.vetapp.v1.dao;

import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Appointment;
import art.dborg.vetapp.v1.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This interface provides methods for accessing appointment data in the database.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    /**
     * Checks if an appointment exists at the specified date and time.
     *
     * @param dateTime The date and time of the appointment.
     * @return True if an appointment exists at the specified date and time, false otherwise.
     */
    boolean existsByDateTime(LocalDateTime dateTime);

    /**
     * Checks if appointments exist for a doctor with the specified ID.
     *
     * @param id The ID of the doctor.
     * @return True if appointments exist for the doctor, false otherwise.
     */
    boolean existsByDoctor_Id(long id);

    /**
     * Finds appointments between the specified start and end dates for a specific doctor.
     *
     * @param startDate The start date and time.
     * @param endDate   The end date and time.
     * @param doctor    The doctor for whom appointments are being searched.
     * @return A list of appointments for the specified doctor between the start and end dates.
     */
    List<Appointment> findByDateTimeBetweenAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor);

    /**
     * Checks if appointments exist between the specified start and end dates.
     *
     * @param startDate The start date and time.
     * @param endDate   The end date and time.
     * @return True if appointments exist between the start and end dates, false otherwise.
     */
    boolean existsByDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Finds appointments between the specified start and end dates for a specific animal.
     *
     * @param startDate The start date and time.
     * @param endDate   The end date and time.
     * @param animal    The animal for which appointments are being searched.
     * @return A list of appointments for the specified animal between the start and end dates.
     */
    List<Appointment> findByDateTimeBetweenAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal);

    /**
     * Checks if appointments exist for an available date with the specified ID.
     *
     * @param id The ID of the available date.
     * @return True if appointments exist for the available date, false otherwise.
     */
    boolean existsByAvailableDate_Id(long id);

    /**
     * Checks if an entry exists in the database based on a combination of doctor ID, animal ID, and available date ID.
     *
     * @param doctorId    The ID of the doctor.
     * @param animalId    The ID of the animal.
     * @param availableId The ID of the available date.
     * @return {@code true} if an entry exists with the provided criteria, {@code false} otherwise.
     */
    boolean existsByDoctor_IdAndAnimal_IdAndAvailableDate_Id(long doctorId, long animalId, long availableId);
}

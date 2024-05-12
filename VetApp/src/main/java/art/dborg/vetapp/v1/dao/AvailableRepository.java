package art.dborg.vetapp.v1.dao;

import art.dborg.vetapp.v1.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * This interface provides methods for accessing available date data in the database.
 */
@Repository
public interface AvailableRepository extends JpaRepository<AvailableDate, Long> {
    /**
     * Checks if an available date exists for a specific date and doctor ID.
     *
     * @param date The date of the available date.
     * @param id   The ID of the doctor.
     * @return True if an available date exists for the specified date and doctor ID, false otherwise.
     */
    boolean existsByDateAndDoctors_Id(LocalDate date, long id);

    /**
     * Checks if an entry exists in the database based on a combination of date ID, date, and doctor ID.
     *
     * @param dateId The ID of the date.
     * @param date   The specific date.
     * @param id     The ID of the doctor.
     * @return {@code true} if an entry exists with the provided criteria, {@code false} otherwise.
     */
    boolean existsByIdAndDateAndDoctors_Id(long dateId, LocalDate date, long id);

    /**
     * Finds an available date for a specific doctor ID.
     *
     * @param id The ID of the doctor.
     * @return The available date for the specified doctor ID.
     */
    AvailableDate findByDoctors_Id(long id);

    /**
     * Finds an available date for a specific date.
     *
     * @param date The date of the available date.
     * @return The available date for the specified date.
     */
    AvailableDate findByDate(LocalDate date);
}

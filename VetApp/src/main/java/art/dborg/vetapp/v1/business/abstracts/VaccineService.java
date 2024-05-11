package art.dborg.vetapp.v1.business.abstracts;

import art.dborg.vetapp.v1.entities.Vaccine;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface defines methods for managing vaccine entities.
 */
public interface VaccineService {
    /**
     * Adds a new vaccine.
     * @param vaccine The vaccine to add.
     * @return The added vaccine.
     */
    Vaccine addVaccine(Vaccine vaccine);

    /**
     * Retrieves a vaccine by its ID.
     * @param id The ID of the vaccine to retrieve.
     * @return The retrieved vaccine.
     */
    Vaccine getId(long id);

    /**
     * Updates an existing vaccine.
     * @param vaccine The vaccine to update.
     * @return The updated vaccine.
     */
    Vaccine update(Vaccine vaccine);

    /**
     * Forces an update on an existing vaccine.
     * @param vaccine The vaccine to update.
     * @return The updated vaccine.
     */
    Vaccine forceUpdate(Vaccine vaccine);

    /**
     * Deletes a vaccine by its ID.
     * @param id The ID of the vaccine to delete.
     * @return A boolean indicating whether the deletion was successful.
     */
    boolean delete(long id);

    /**
     * Retrieves a list of vaccines associated with a specific animal.
     * @param id The ID of the animal.
     * @return A list of vaccines associated with the animal.
     */
    List<Vaccine> getAnimalVaccineList(long id);

    /**
     * Retrieves a list of vaccines filtered by their end date.
     * @param endDate The end date to filter by.
     * @return A list of vaccines filtered by the end date.
     */
    List<Vaccine> getFilterByStartAndEndDate(LocalDate startDate,LocalDate endDate);
}

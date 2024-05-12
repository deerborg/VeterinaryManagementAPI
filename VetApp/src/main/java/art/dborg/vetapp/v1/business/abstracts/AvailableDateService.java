package art.dborg.vetapp.v1.business.abstracts;

import art.dborg.vetapp.v1.entities.AvailableDate;

/**
 * This interface defines methods for managing available date entities.
 */
public interface AvailableDateService {
    /**
     * Saves a new available date.
     * @param availableDate The available date to save.
     * @return The saved available date.
     */
    AvailableDate save(AvailableDate availableDate);

    /**
     * Updates an existing available date.
     * @param availableDate The available date to update.
     * @return The updated available date.
     */
    AvailableDate update(AvailableDate availableDate);

    /**
     * Retrieves an available date by its ID.
     * @param id The ID of the available date to retrieve.
     * @return The retrieved available date.
     */
    AvailableDate getId(long id);

    /**
     * Deletes an available date by its ID.
     * @param id The ID of the available date to delete.
     * @return A boolean indicating whether the deletion was successful.
     */
    boolean delete(long id);

    boolean forceDelete(long id);
}

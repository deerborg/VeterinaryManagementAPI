package art.dborg.vetapp.v1.business.abstracts;

import art.dborg.vetapp.v1.entities.Doctor;

/**
 * This interface defines methods for managing doctor entities.
 */
public interface DoctorService {
    /**
     * Saves a new doctor.
     * @param doctor The doctor to save.
     * @return The saved doctor.
     */
    Doctor save(Doctor doctor);

    /**
     * Updates an existing doctor.
     * @param doctor The doctor to update.
     * @return The updated doctor.
     */
    Doctor update(Doctor doctor);

    /**
     * Retrieves a doctor by its ID.
     * @param id The ID of the doctor to retrieve.
     * @return The retrieved doctor.
     */
    Doctor getId(long id);

    /**
     * Deletes a doctor by its ID.
     * @param id The ID of the doctor to delete.
     * @return A boolean indicating whether the deletion was successful.
     */
    boolean delete(long id);
}

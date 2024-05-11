package art.dborg.vetapp.v1.dao;

import art.dborg.vetapp.v1.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface provides methods for accessing vaccine data in the database.
 */
@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    /**
     * Retrieves a list of vaccines associated with the specified animal ID.
     *
     * @param animalId The ID of the animal.
     * @return A list of vaccines associated with the specified animal ID.
     */
    List<Vaccine> findByAnimal_Id(long animalId);

    /**
     * Checks if a vaccine exists with the specified code, name, and animal ID.
     *
     * @param code The code of the vaccine.
     * @param name The name of the vaccine.
     * @param id   The ID of the animal.
     * @return True if a vaccine exists with the specified code, name, and animal ID, false otherwise.
     */
    boolean existsVaccineByCodeAndNameAndAnimal_id(String code, String name, long id);

    /**
     * Retrieves a list of vaccines with the specified end date.
     *
     * @param endDate The end date of the vaccines.
     * @return A list of vaccines with the specified end date.
     */
    List<Vaccine> findByEndDateBetween(LocalDate firstDate, LocalDate endDate);

    /**
     * Retrieves the vaccine with the specified start date.
     *
     * @param startDate The start date of the vaccine.
     * @return The vaccine with the specified start date.
     */
    Vaccine findByStartDate(LocalDate startDate);

    /**
     * Checks if a vaccine exists with the specified end date.
     *
     * @param endDate The end date of the vaccine.
     * @return True if a vaccine exists with the specified end date, false otherwise.
     */
    boolean existsByEndDate(LocalDate endDate);


    /**
     * Counts the number of vaccines with the specified name, code, and animal ID.
     * <p>
     * This method counts the number of vaccines in the system that match the given name, code,
     * and animal ID. It provides a count of how many vaccines meet these criteria.
     *
     * @param name     The name of the vaccine to count.
     * @param code     The code of the vaccine to count.
     * @param animalId The ID of the animal associated with the vaccine.
     * @return The count of vaccines with the specified name, code, and animal ID.
     */
    long countVaccineByNameAndCodeAndAnimal_Id(String name, String code, long animalId);
}

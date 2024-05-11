package art.dborg.vetapp.v1.dao;

import art.dborg.vetapp.v1.entities.Appointment;
import art.dborg.vetapp.v1.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface provides methods for accessing doctor data in the database.
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    /**
     * Checks if a doctor exists with the specified email or phone number.
     * @param mail The email to check.
     * @param phone The phone number to check.
     * @return True if a doctor exists with the specified email or phone number, false otherwise.
     */
    boolean existsByMailOrPhone(String mail, String phone);
}

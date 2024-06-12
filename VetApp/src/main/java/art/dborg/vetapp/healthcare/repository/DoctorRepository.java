package art.dborg.vetapp.healthcare.repository;

import art.dborg.vetapp.healthcare.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByMailOrPhone(String mail, String phone);
    Doctor findByName(String name);
}

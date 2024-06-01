package art.dborg.vetapp.v1.dao;

import art.dborg.vetapp.v1.entities.Appointment;
import art.dborg.vetapp.v1.entities.Customer;
import art.dborg.vetapp.v1.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByMailOrPhone(String mail, String phone);
    Doctor findByName(String name);
}

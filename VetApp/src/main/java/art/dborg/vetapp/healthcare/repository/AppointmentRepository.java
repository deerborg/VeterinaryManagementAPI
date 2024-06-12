package art.dborg.vetapp.healthcare.repository;

import art.dborg.vetapp.petcare.model.entity.Animal;
import art.dborg.vetapp.healthcare.model.entity.Appointment;
import art.dborg.vetapp.healthcare.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDateTime(LocalDateTime dateTime);
    boolean existsByDoctor_Id(long id);
    List<Appointment> findByDateTimeBetweenAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor);
    boolean existsByDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> findByDateTimeBetweenAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal);
    boolean existsByAvailableDate_Id(long id);
    boolean existsByDoctor_IdAndAnimal_Id(long doctorId, long animalId);
}

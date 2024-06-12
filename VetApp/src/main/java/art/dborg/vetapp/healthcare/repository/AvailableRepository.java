package art.dborg.vetapp.healthcare.repository;

import art.dborg.vetapp.healthcare.model.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface AvailableRepository extends JpaRepository<AvailableDate, Long> {
    boolean existsByDateAndDoctors_Id(LocalDate date, long id);
    boolean existsByIdAndDateAndDoctors_Id(long dateId, LocalDate date, long id);
    AvailableDate findByDoctors_Id(long id);
    AvailableDate findByDate(LocalDate date);

    @Query("select a.id from AvailableDate a where a.date = :endDate and a.doctors.id = :doctorId")
    Long findByAvailableIdInEndDateAndDoctorId(@Param("endDate") LocalDate endDate, @Param("doctorId") long doctorId);

    boolean existsAvailableDateByDate(LocalDate date);
}

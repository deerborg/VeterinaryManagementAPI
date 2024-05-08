package art.dborg.vetapp.v1.dao;

import art.dborg.vetapp.v1.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AvailableRepository extends JpaRepository<AvailableDate,Long> {
    boolean existsByDateAndDoctors_Id(LocalDate date,long id);
}

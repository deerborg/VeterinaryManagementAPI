package art.dborg.vetapp.v1.dao;

import art.dborg.vetapp.v1.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine,Long> {
    List<Vaccine> findByAnimal_Id(long animalId);
    boolean existsVaccineByCodeAndName(String code,String name);
    Vaccine findByEndDate(LocalDate endDate);
    Vaccine findByStartDate(LocalDate startDate);
}

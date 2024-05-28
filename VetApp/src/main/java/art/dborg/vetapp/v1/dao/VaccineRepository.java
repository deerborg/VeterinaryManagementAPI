package art.dborg.vetapp.v1.dao;

import art.dborg.vetapp.v1.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findByAnimal_Id(long animalId);
    boolean existsVaccineByCodeAndNameAndAnimal_id(String code, String name, long id);
    List<Vaccine> findByEndDateBetween(LocalDate firstDate, LocalDate endDate);
    Vaccine findByStartDate(LocalDate startDate);
    boolean existsByEndDate(LocalDate endDate);
    long countVaccineByNameAndCodeAndAnimal_Id(String name, String code, long animalId);
    List<Vaccine> findByEndDateAfterOrderByEndDate(LocalDate endDate);

}

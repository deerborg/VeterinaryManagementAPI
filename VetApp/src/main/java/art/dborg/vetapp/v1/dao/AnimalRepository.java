package art.dborg.vetapp.v1.dao;

import art.dborg.vetapp.v1.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {
    List<Animal> findByCustomer_Id(long animalCustomerId); // id göre müşteriye ait hayvanları listeler
    List<Animal> findByName(String name); // isme göre hayvanları getirir, aynı isimde hayvan olma durumundan dolayı liste döndürdüm
}

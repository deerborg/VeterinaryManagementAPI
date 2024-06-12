package art.dborg.vetapp.petcare.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "vaccines")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id",columnDefinition = "serial")
    private long id;

    @Column(name = "vaccine_name")
    private String name;

    @Column(name = "vaccine_code")
    private String code;

    @Column(name = "vaccine_protection_start_date")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Column(name = "vaccine_protection_end_date")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vaccine_animal_id",referencedColumnName = "animal_id")
    private Animal animal;

    public Vaccine() {
    }

    public Vaccine(long id, String name, String code, LocalDate startDate, LocalDate endDate, Animal animal) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.animal = animal;
    }
}

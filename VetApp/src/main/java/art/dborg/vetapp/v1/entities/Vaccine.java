package art.dborg.vetapp.v1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "vaccines")
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id",columnDefinition = "serial")
    private long id; // Unique identifier for the vaccine

    @Column(name = "vaccine_name")
    private String name; // Name of the vaccine

    @Column(name = "vaccine_code")
    private String code; // Code of the vaccine

    @Column(name = "vaccine_protection_start_date")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate; // Start date of protection provided by the vaccine

    @Column(name = "vaccine_protection_end_date")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate; // End date of protection provided by the vaccine

    @ManyToOne(fetch = FetchType.EAGER) // Section - 9 : Relationships between entities
    @JoinColumn(name = "vaccine_animal_id",referencedColumnName = "animal_id")
    private Animal animal; // Animal associated with the vaccine
}

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
@Table(name = "animals")
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", columnDefinition = "serial")
    private long id; // Unique identifier for the animal

    @Column(name = "animal_name")
    private String name; // Name of the animal

    @Column(name = "animal_species")
    private String species; // Species of the animal

    @Column(name = "animal_breed")
    private String breed; // Breed of the animal

    @Column(name = "animal_gender")
    private String gender; // Gender of the animal

    @Column(name = "animal_colour")
    private String colour; // Colour of the animal

    @Column(name = "animal_date_of_birth")
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth; // Date of birth of the animal

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_customer_id", referencedColumnName = "customer_id")
    private Customer customer; // Owner of the animal

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Vaccine> vaccines; // Vaccines for the animal

    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> appointments; // Appointments for the animal
}

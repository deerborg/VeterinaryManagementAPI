package art.dborg.vetapp.petcare.model.entity;

import art.dborg.vetapp.healthcare.model.entity.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", columnDefinition = "serial")
    private long id;

    @Column(name = "animal_name")
    private String name;

    @Column(name = "animal_species")
    private String species;

    @Column(name = "animal_breed")
    private String breed;

    @Column(name = "animal_gender")
    private String gender;

    @Column(name = "animal_colour")
    private String colour;

    @Column(name = "animal_date_of_birth")
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @Column(name = "animal_age")
    private Integer age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_customer_id", referencedColumnName = "customer_id")
    private Customer customer; // Owner of the animal

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Vaccine> vaccines;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> appointments;

    public Animal() {
    }

    public Animal(long id, String name, String species, String breed, String gender, String colour, LocalDate dateOfBirth, Integer age, Customer customer, List<Vaccine> vaccines, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.colour = colour;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.customer = customer;
        this.vaccines = vaccines;
        this.appointments = appointments;
    }
}

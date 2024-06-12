package art.dborg.vetapp.petcare.model.dto.animal;

import art.dborg.vetapp.petcare.model.entity.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class AnimalSaveRequest {
    @NotNull(message = "Animal name cannot be null.")
    private String name; // Name of the animal

    @NotNull(message = "Animal species cannot be null.")
    private String species; // Species of the animal

    @NotNull(message = "Animal breed cannot be null.")
    private String breed; // Breed of the animal

    @NotNull(message = "Animal gender cannot be null.")
    private String gender; // Gender of the animal

    @NotNull(message = "Animal colour cannot be null.")
    private String colour; // Colour of the animal

    @NotNull(message = "Animal birth date cannot be null.")
    private LocalDate dateOfBirth; // Date of birth of the animal

    private Integer age;

    @NotNull(message = "Animal customer ID cannot be null.")
    private Customer customer; // ID of the customer who owns the animal

    public AnimalSaveRequest() {
    }

    public AnimalSaveRequest(String name, String species, String breed, String gender, String colour, LocalDate dateOfBirth, Integer age, Customer customer) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.colour = colour;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.customer = customer;
    }
}

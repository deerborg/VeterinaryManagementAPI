package art.dborg.vetapp.v1.dto.response.animal;

import art.dborg.vetapp.v1.entities.Customer;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class AnimalsResponse {
    private String name; // Name of the animal

    private String species; // Species of the animal

    private String breed; // Breed of the animal

    private String gender; // Gender of the animal

    private String colour; // Colour of the animal

    private LocalDate dateOfBirth; // Date of birth of the animal

    private Integer age;

    private Customer customer; // Customer who owns the animal

}

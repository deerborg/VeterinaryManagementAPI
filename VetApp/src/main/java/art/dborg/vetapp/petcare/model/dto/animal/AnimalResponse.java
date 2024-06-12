package art.dborg.vetapp.petcare.model.dto.animal;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class AnimalResponse {
    private String name; // Name of the animal

    private String species; // Species of the animal

    private String breed; // Breed of the animal

    private String gender; // Gender of the animal

    private String colour; // Colour of the animal

    private LocalDate dateOfBirth; // Date of birth of the animal

    private Integer age;
}

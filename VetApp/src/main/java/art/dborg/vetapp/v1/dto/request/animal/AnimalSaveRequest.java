package art.dborg.vetapp.v1.dto.request.animal;

import art.dborg.vetapp.v1.entities.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}

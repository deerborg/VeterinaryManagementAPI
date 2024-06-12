package art.dborg.vetapp.petcare.model.dto.vaccine;

import art.dborg.vetapp.petcare.model.entity.Animal;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class VaccineUpdateRequest {
    @NotNull(message = "ID cannot be null.")
    private long id; // ID of the vaccine to be updated

    @NotNull(message = "Vaccine name cannot be null.")
    private String name; // Name of the vaccine

    @NotNull(message = "Vaccine code cannot be null.")
    private String code; // Code of the vaccine

    @NotNull(message = "Vaccine start date cannot be null.")
    private LocalDate startDate; // Start date of the vaccine

    @NotNull(message = "Vaccine end date cannot be null.")
    private LocalDate endDate; // End date of the vaccine

    @NotNull(message = "Animal ID cannot be null.")
    private Animal animal; // ID of the animal associated with the vaccine
}

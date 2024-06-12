package art.dborg.vetapp.petcare.model.dto.vaccine;

import art.dborg.vetapp.petcare.model.entity.Animal;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class VaccinesResponse {
    private String name; // Name of the vaccine

    private String code; // Code of the vaccine

    private LocalDate startDate; // Start date of the vaccine

    private LocalDate endDate; // End date of the vaccine

    private Animal animal; // Animal associated with the vaccine
}

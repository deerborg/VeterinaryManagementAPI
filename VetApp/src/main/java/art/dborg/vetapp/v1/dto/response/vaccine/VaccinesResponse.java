package art.dborg.vetapp.v1.dto.response.vaccine;

import art.dborg.vetapp.v1.entities.Animal;
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

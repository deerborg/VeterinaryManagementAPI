package art.dborg.vetapp.v1.dto.response.vaccine;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class VaccineResponse {
    private String name; // Name of the vaccine

    private String code; // Code of the vaccine

    private LocalDate startDate; // Start date of the vaccine

    private LocalDate endDate; // End date of the vaccine
}

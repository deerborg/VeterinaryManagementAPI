package art.dborg.vetapp.v1.dto.response.vaccine;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineResponse {
    private String name; // Name of the vaccine

    private String code; // Code of the vaccine

    private LocalDate startDate; // Start date of the vaccine

    private LocalDate endDate; // End date of the vaccine
}

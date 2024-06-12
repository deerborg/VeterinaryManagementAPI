package art.dborg.vetapp.petcare.model.dto.vaccine;

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

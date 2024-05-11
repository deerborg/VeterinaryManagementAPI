package art.dborg.vetapp.v1.dto.request.availableDate;

import art.dborg.vetapp.v1.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateUpdateRequest {
    @NotNull(message = "ID cannot be null.")
    private long id; // ID of the available date to be updated

    @NotNull(message = "Date cannot be null.")
    private LocalDate date; // Date for the available date

    @NotNull(message = "Doctor ID cannot be null.")
    private Doctor doctors; // ID of the doctor for the available date
}

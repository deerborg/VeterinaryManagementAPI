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
    @NotNull
    private long id;
    @NotNull
    private LocalDate date;
    @NotNull
    private Doctor doctor;
}

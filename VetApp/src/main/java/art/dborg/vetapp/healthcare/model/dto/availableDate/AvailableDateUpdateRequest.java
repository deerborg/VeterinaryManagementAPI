package art.dborg.vetapp.healthcare.model.entity.dto.availableDate;

import art.dborg.vetapp.healthcare.model.entity.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class AvailableDateUpdateRequest {
    @NotNull(message = "ID cannot be null.")
    private long id; // ID of the available date to be updated

    @NotNull(message = "Date cannot be null.")
    private LocalDate date; // Date for the available date

    @NotNull(message = "Doctor ID cannot be null.")
    private Doctor doctors; // ID of the doctor for the available date

    public AvailableDateUpdateRequest() {
    }

    public AvailableDateUpdateRequest(long id, LocalDate date, Doctor doctors) {
        this.id = id;
        this.date = date;
        this.doctors = doctors;
    }
}

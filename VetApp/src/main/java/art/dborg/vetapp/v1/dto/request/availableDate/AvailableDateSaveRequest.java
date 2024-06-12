package art.dborg.vetapp.v1.dto.request.availableDate;

import art.dborg.vetapp.v1.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class AvailableDateSaveRequest {
    @NotNull(message = "Date cannot be null.")
    private LocalDate date; // Date for the available date

    @NotNull(message = "Doctor ID cannot be null.")
    private Doctor doctors; // ID of the doctor for the available date

    public AvailableDateSaveRequest() {
    }

    public AvailableDateSaveRequest(LocalDate date, Doctor doctors) {
        this.date = date;
        this.doctors = doctors;
    }
}

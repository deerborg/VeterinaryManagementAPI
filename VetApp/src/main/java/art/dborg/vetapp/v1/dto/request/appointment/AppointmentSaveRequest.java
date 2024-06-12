package art.dborg.vetapp.v1.dto.request.appointment;

import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.AvailableDate;
import art.dborg.vetapp.v1.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentSaveRequest {
    @NotNull(message = "Animal ID cannot be null.")
    private Animal animal;

    @NotNull(message = "Doctor ID cannot be null.")
    private Doctor doctor;

    @NotNull(message = "Date Time cannot be null.")
    private LocalDateTime dateTime;

    private AvailableDate availableDate;

    public AppointmentSaveRequest() {
    }

    public AppointmentSaveRequest(Animal animal, Doctor doctor, LocalDateTime dateTime, AvailableDate availableDate) {
        this.animal = animal;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.availableDate = availableDate;
    }
}

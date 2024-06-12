package art.dborg.vetapp.healthcare.model.entity.dto.appointment;

import art.dborg.vetapp.petcare.model.entity.Animal;
import art.dborg.vetapp.healthcare.model.entity.AvailableDate;
import art.dborg.vetapp.healthcare.model.entity.Doctor;
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

package art.dborg.vetapp.healthcare.model.entity.dto.appointment;

import art.dborg.vetapp.petcare.model.entity.Animal;
import art.dborg.vetapp.healthcare.model.entity.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentUpdateRequest {
    @NotNull(message = "ID cannot be null.")
    private long id; // ID of the appointment to be updated

    @NotNull(message = "Animal ID cannot be null.")
    private Animal animal; // ID of the animal for the appointment

    @NotNull(message = "Doctor ID cannot be null.")
    private Doctor doctor; // ID of the doctor for the appointment

    @NotNull(message = "Date Time cannot be null.")
    private LocalDateTime dateTime; // Date and time of the appointment

    public AppointmentUpdateRequest() {
    }

    public AppointmentUpdateRequest(long id, Animal animal, Doctor doctor, LocalDateTime dateTime) {
        this.id = id;
        this.animal = animal;
        this.doctor = doctor;
        this.dateTime = dateTime;
    }
}

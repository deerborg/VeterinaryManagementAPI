package art.dborg.vetapp.healthcare.model.entity.dto.appointment;

import art.dborg.vetapp.petcare.model.entity.Animal;
import art.dborg.vetapp.healthcare.model.entity.Doctor;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentsResponse {
    private LocalDateTime dateTime; // Date and time of the appointment

    private Doctor doctor; // Doctor associated with the appointment

    private Animal animal; // Animal associated with the appointment
}

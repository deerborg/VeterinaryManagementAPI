package art.dborg.vetapp.v1.dto.response.appointment;

import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Doctor;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentsResponse {
    private LocalDateTime dateTime; // Date and time of the appointment

    private Doctor doctor; // Doctor associated with the appointment

    private Animal animal; // Animal associated with the appointment
}

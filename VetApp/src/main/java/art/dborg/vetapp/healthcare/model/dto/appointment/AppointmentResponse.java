package art.dborg.vetapp.healthcare.model.entity.dto.appointment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentResponse {
    private LocalDateTime dateTime; // Date and time of the appointment
}

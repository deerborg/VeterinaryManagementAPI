package art.dborg.vetapp.v1.dto.response.appointment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentResponse {
    private LocalDateTime dateTime; // Date and time of the appointment
}

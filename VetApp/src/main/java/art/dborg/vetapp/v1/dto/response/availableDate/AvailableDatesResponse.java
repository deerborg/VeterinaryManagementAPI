package art.dborg.vetapp.v1.dto.response.availableDate;

import art.dborg.vetapp.v1.entities.Doctor;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class AvailableDatesResponse {
    private LocalDate date; // Date of the available date

    private Doctor doctors; // Doctor associated with the available date
}

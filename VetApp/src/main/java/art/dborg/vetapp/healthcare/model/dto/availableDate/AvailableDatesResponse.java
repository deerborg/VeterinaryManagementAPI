package art.dborg.vetapp.healthcare.model.entity.dto.availableDate;

import art.dborg.vetapp.healthcare.model.entity.Doctor;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class AvailableDatesResponse {
    private LocalDate date; // Date of the available date

    private Doctor doctors; // Doctor associated with the available date
}

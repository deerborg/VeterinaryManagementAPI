package art.dborg.vetapp.healthcare.model.entity.dto.availableDate;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class AvailableDateResponse {
    private LocalDate date; // Date of the available date
}

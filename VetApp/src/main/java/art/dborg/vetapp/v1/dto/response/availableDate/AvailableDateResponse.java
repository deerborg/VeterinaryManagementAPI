package art.dborg.vetapp.v1.dto.response.availableDate;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class AvailableDateResponse {
    private LocalDate date; // Date of the available date
}

package art.dborg.vetapp.v1.dto.response.availableDate;

import art.dborg.vetapp.v1.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateGetAllResponse {
    private LocalDate date; // Date of the available date

    private Doctor doctors; // Doctor associated with the available date
}

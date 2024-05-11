package art.dborg.vetapp.v1.dto.response.doctor;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DoctorResponse {
    private String name; // Name of the doctor
}

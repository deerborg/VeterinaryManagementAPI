package art.dborg.vetapp.healthcare.model.entity.dto.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class DoctorUpdateRequest {
    @NotNull(message = "ID cannot be null.")
    private long id; // ID of the doctor to be updated

    @NotNull(message = "Doctor name cannot be null.")
    private String name; // Name of the doctor

    @NotNull(message = "Doctor phone cannot be null.")
    private String phone; // Phone number of the doctor

    @NotNull(message = "Doctor mail cannot be null.")
    @Email(message = "Email format is wrong. Format : mail@mail.com")
    private String mail; // Email address of the doctor

    @NotNull(message = "Doctor address cannot be null.")
    private String address; // Address of the doctor

    @NotNull(message = "Doctor city cannot be null.")
    private String city; // City of the doctor
}

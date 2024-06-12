package art.dborg.vetapp.healthcare.model.entity.dto.doctor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorsResponse {
    private Long id;
    private String name;
    private String mail;
    private String phone;
    private String address;
    private String city;
}

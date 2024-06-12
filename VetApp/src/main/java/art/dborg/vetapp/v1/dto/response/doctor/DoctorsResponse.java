package art.dborg.vetapp.v1.dto.response.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

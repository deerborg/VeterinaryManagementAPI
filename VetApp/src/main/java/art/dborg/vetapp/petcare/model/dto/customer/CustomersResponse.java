package art.dborg.vetapp.petcare.model.dto.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomersResponse {
    private Long id;
    private String name;
    private String mail;
    private String phone;
    private String address;
    private String city;
}

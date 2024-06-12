package art.dborg.vetapp.v1.dto.response.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class CustomerResponse {
    private Long id;
    private String name; // Name of the customer
}

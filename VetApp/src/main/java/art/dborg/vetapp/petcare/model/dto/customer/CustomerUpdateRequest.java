package art.dborg.vetapp.petcare.model.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class CustomerUpdateRequest {
    @NotNull(message = "ID cannot be null.")
    private long id; // ID of the customer to be updated

    @NotNull(message = "Customer name cannot be null.")
    private String name; // Name of the customer

    @NotNull(message = "Customer phone cannot be null.")
    private String phone; // Phone number of the customer

    @NotNull(message = "Customer mail cannot be null.")
    @Email(message = "Email format is wrong. Format : mail@mail.com")
    private String mail; // Email address of the customer

    @NotNull(message = "Customer address cannot be null.")
    private String address; // Address of the customer

    @NotNull(message = "Customer city cannot be null.")
    private String city; // City of the customer
}

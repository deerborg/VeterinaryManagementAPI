package art.dborg.vetapp.v1.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {
    @NotNull
    private String name;
    @NotNull
    private String phone;
    @NotNull
    @Email
    private String mail;
    @NotNull
    private String address;
    @NotNull
    private String city;
}

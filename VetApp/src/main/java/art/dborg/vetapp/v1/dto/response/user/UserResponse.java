package art.dborg.vetapp.v1.dto.response.user;

import art.dborg.vetapp.v1.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private LocalDate registerDate;
    private List<Role> roles;
    private boolean enabled;
}

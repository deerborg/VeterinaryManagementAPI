package art.dborg.vetapp.auth.model.dto;

import art.dborg.vetapp.auth.model.entity.Role;
import lombok.Getter;
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

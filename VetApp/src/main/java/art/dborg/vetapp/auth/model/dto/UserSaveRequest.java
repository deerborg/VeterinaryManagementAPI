package art.dborg.vetapp.auth.model.dto;

import art.dborg.vetapp.auth.model.entity.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserSaveRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;

    private List<Role> roles;

    private LocalDate registerDate;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
}

package art.dborg.vetapp.auth.model.dto;

import art.dborg.vetapp.auth.model.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserUpdateRequest {
    private Long id;
    private List<Role> roles;
}

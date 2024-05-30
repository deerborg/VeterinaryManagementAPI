package art.dborg.vetapp.v1.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
public enum Role implements GrantedAuthority {
    ADMIN,MODERATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}

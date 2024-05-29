package art.dborg.vetapp.v1.service.concretes;

import art.dborg.vetapp.v1.core.exception.NotFoundUserPasswordOrUsername;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getByUsername(username);
    }
}

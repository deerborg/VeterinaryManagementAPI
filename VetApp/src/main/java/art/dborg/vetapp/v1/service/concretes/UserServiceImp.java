package art.dborg.vetapp.v1.service.concretes;

import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.exception.NotFoundUserPasswordOrUsername;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dao.UserRepository;
import art.dborg.vetapp.v1.dto.request.user.UserSaveRequest;
import art.dborg.vetapp.v1.dto.response.user.UserResponse;
import art.dborg.vetapp.v1.entities.Role;
import art.dborg.vetapp.v1.entities.User;
import art.dborg.vetapp.v1.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final ModelMapperService mapperService;
    @Override
    public ResultData<UserResponse> createUser(UserSaveRequest user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(Role.MODERATOR));
        return ResultHelper.CREATED(mapperService.forResponse().map(userRepository.save(mapperService.forRequest().map(user,User.class)),UserResponse.class));
    }

    @Override
    public User getByUsername(String name) {
        return userRepository.findByUsername(name);
    }
}

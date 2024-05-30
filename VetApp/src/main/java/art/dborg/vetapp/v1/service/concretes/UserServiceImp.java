package art.dborg.vetapp.v1.service.concretes;

import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.exception.NotFoundException;
import art.dborg.vetapp.v1.core.exception.NotFoundUserPasswordOrUsername;
import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dao.UserRepository;
import art.dborg.vetapp.v1.dto.request.user.UserSaveRequest;
import art.dborg.vetapp.v1.dto.request.user.UserUpdateRequest;
import art.dborg.vetapp.v1.dto.response.user.UserResponse;
import art.dborg.vetapp.v1.entities.Role;
import art.dborg.vetapp.v1.entities.User;
import art.dborg.vetapp.v1.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        user.setRegisterDate(LocalDate.now());
        return ResultHelper.CREATED(mapperService.forResponse().map(userRepository.save(mapperService.forRequest().map(user, User.class)), UserResponse.class));
    }

    @Override
    public User getByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public ResultData<List<UserResponse>> getAllUser() {
        return ResultHelper.OK(userRepository.findAll().stream().map(user -> mapperService.forResponse().map(user, UserResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public ResultData<UserResponse> updateUser(UserUpdateRequest user) {
        User user1 =  userRepository.findById(user.getId()).orElse(null);
        user1.setRoles(user.getRoles());
        User updateUser = userRepository.save(user1);
        UserResponse userResponse = mapperService.forResponse().map(updateUser, UserResponse.class);
        return ResultHelper.OK(userResponse);
    }

    @Override
    public Result disabledUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID));

        user.setEnabled(false);
        user.setAccountNonLocked(false);
        user.setAccountNonExpired(false);
        user.setCredentialsNonExpired(false);

        userRepository.save(user);
        return new Result(true, "Saved", "201");
    }
}

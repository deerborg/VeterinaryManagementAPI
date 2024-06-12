package art.dborg.vetapp.auth.service.impl;

import art.dborg.vetapp.auth.model.entity.Role;
import art.dborg.vetapp.auth.model.entity.User;
import art.dborg.vetapp.auth.repository.UserRepository;
import art.dborg.vetapp.auth.service.UserService;
import art.dborg.vetapp.common.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.common.exception.NotFoundException;
import art.dborg.vetapp.common.result.Result;
import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.common.utilities.Message;
import art.dborg.vetapp.common.utilities.ResultHelper;
import art.dborg.vetapp.auth.model.dto.UserSaveRequest;
import art.dborg.vetapp.auth.model.dto.UserUpdateRequest;
import art.dborg.vetapp.auth.model.dto.UserResponse;
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

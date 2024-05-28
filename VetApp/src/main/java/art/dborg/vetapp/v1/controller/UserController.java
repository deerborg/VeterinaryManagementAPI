package art.dborg.vetapp.v1.controller;

import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.dto.request.user.UserSaveRequest;
import art.dborg.vetapp.v1.dto.response.user.UserResponse;
import art.dborg.vetapp.v1.entities.User;
import art.dborg.vetapp.v1.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<UserResponse> createUser(@RequestBody UserSaveRequest user){
        return userService.createUser(user);
    }
}

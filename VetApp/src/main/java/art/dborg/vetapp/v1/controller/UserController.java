package art.dborg.vetapp.v1.controller;

import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.dto.request.user.UserSaveRequest;
import art.dborg.vetapp.v1.dto.request.user.UserUpdateRequest;
import art.dborg.vetapp.v1.dto.response.user.UserResponse;
import art.dborg.vetapp.v1.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping
    public ResultData<UserResponse> updateUser(@RequestBody UserUpdateRequest user){
        return userService.updateUser(user);
    }
    @GetMapping
    public ResultData<List<UserResponse>> getUser(){
        return userService.getAllUser();
    }
    @PutMapping("/disabled/{id}")
    public Result disabledUser(@PathVariable Long id){
        return userService.disabledUser(id);
    }
}

package art.dborg.vetapp.auth.controller;

import art.dborg.vetapp.auth.service.UserService;
import art.dborg.vetapp.common.result.Result;
import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.auth.model.dto.UserSaveRequest;
import art.dborg.vetapp.auth.model.dto.UserUpdateRequest;
import art.dborg.vetapp.auth.model.dto.UserResponse;
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

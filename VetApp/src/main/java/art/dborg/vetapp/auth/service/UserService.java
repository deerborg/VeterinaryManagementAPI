package art.dborg.vetapp.auth.service;

import art.dborg.vetapp.auth.model.entity.User;
import art.dborg.vetapp.common.result.Result;
import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.auth.model.dto.UserSaveRequest;
import art.dborg.vetapp.auth.model.dto.UserUpdateRequest;
import art.dborg.vetapp.auth.model.dto.UserResponse;

import java.util.List;

public interface UserService {
    ResultData<UserResponse> createUser(UserSaveRequest user);
    User getByUsername(String name);
    ResultData<List<UserResponse>> getAllUser();
    ResultData<UserResponse> updateUser(UserUpdateRequest user);
    Result disabledUser(Long id);
}

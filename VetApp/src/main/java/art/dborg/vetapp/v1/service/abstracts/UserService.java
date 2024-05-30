package art.dborg.vetapp.v1.service.abstracts;

import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.dto.request.user.UserSaveRequest;
import art.dborg.vetapp.v1.dto.request.user.UserUpdateRequest;
import art.dborg.vetapp.v1.dto.response.user.UserResponse;
import art.dborg.vetapp.v1.entities.User;

import java.util.List;

public interface UserService {
    ResultData<UserResponse> createUser(UserSaveRequest user);
    User getByUsername(String name);
    ResultData<List<UserResponse>> getAllUser();
    ResultData<UserResponse> updateUser(UserUpdateRequest user);
    Result disabledUser(Long id);
}

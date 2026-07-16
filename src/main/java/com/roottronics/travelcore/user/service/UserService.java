package com.roottronics.travelcore.user.service;

import com.roottronics.travelcore.common.response.BaseResponse;
import com.roottronics.travelcore.user.dto.request.CreateUserRequest;
import com.roottronics.travelcore.user.dto.request.UpdateUserRequest;
import com.roottronics.travelcore.user.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    BaseResponse<UserResponse> createUser(CreateUserRequest request);

    BaseResponse<UserResponse> getUserById(Long id);

    BaseResponse<List<UserResponse>> getAllUsers();

    BaseResponse<UserResponse> updateUser(Long id, UpdateUserRequest request);

    BaseResponse<String> deleteUser(Long id);

}

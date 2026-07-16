package com.roottronics.travelcore.user.service.impl;

import com.roottronics.travelcore.common.response.BaseResponse;
import com.roottronics.travelcore.common.enums.Status;
import com.roottronics.travelcore.user.dto.request.CreateUserRequest;
import com.roottronics.travelcore.user.dto.request.UpdateUserRequest;
import com.roottronics.travelcore.user.dto.response.UserResponse;
import com.roottronics.travelcore.user.entity.AppUser;
import com.roottronics.travelcore.user.exception.UserNotFoundException;
import com.roottronics.travelcore.user.mapper.UserMapper;
import com.roottronics.travelcore.user.repository.UserRepository;
import com.roottronics.travelcore.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public BaseResponse<UserResponse> createUser(CreateUserRequest request) {

        if (repository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }

        AppUser appUser = mapper.toEntity(request);

        appUser.setEmployeeCode("EMP-" + System.currentTimeMillis());

        appUser.setPassword(passwordEncoder.encode(request.password()));

        appUser.setStatus(Status.ACTIVE);

        AppUser savedUser = repository.save(appUser);

        return BaseResponse.<UserResponse>builder()
                .success(true)
                .message("User created successfully")
                .data(mapper.toResponse(savedUser))
                .build();
    }

    @Override
    public BaseResponse<UserResponse> getUserById(Long id) {

        AppUser appUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return BaseResponse.<UserResponse>builder()
                .success(true)
                .message("User fetched successfully")
                .data(mapper.toResponse(appUser))
                .build();
    }

    @Override
    public BaseResponse<List<UserResponse>> getAllUsers() {

        List<UserResponse> users = repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return BaseResponse.<List<UserResponse>>builder()
                .success(true)
                .message("Users fetched successfully")
                .data(users)
                .build();
    }

    @Override
    public BaseResponse<UserResponse> updateUser(Long id,
                                                 UpdateUserRequest request) {

        AppUser appUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        appUser.setFirstName(request.firstName());
        appUser.setLastName(request.lastName());
        appUser.setEmail(request.email());
        appUser.setMobile(request.mobile());

        AppUser updated = repository.save(appUser);

        return BaseResponse.<UserResponse>builder()
                .success(true)
                .message("User updated successfully")
                .data(mapper.toResponse(updated))
                .build();
    }

    @Override
    public BaseResponse<String> deleteUser(Long id) {

        AppUser appUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        appUser.setDeleted(true);

        appUser.setStatus(Status.DELETED);

        repository.save(appUser);

        return BaseResponse.<String>builder()
                .success(true)
                .message("User deleted successfully")
                .data("SUCCESS")
                .build();
    }

}

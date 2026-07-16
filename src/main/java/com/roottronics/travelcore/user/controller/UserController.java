package com.roottronics.travelcore.user.controller;

import com.roottronics.travelcore.common.response.BaseResponse;
import com.roottronics.travelcore.user.dto.request.CreateUserRequest;
import com.roottronics.travelcore.user.dto.request.UpdateUserRequest;
import com.roottronics.travelcore.user.dto.response.UserResponse;
import com.roottronics.travelcore.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public BaseResponse<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequest request) {

        return service.createUser(request);
    }

    @GetMapping("/{id}")
    public BaseResponse<UserResponse> getUserById(
            @PathVariable Long id) {

        return service.getUserById(id);
    }

    @GetMapping
    public BaseResponse<List<UserResponse>> getAllUsers() {

        return service.getAllUsers();
    }

    @PutMapping("/{id}")
    public BaseResponse<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {

        return service.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> deleteUser(
            @PathVariable Long id) {

        return service.deleteUser(id);
    }

}

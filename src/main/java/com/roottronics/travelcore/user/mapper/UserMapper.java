package com.roottronics.travelcore.user.mapper;

import com.roottronics.travelcore.user.dto.request.CreateUserRequest;
import com.roottronics.travelcore.user.dto.response.UserResponse;
import com.roottronics.travelcore.user.entity.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public AppUser toEntity(CreateUserRequest request) {

        AppUser user = new AppUser();

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setMobile(request.mobile());
        user.setPassword(request.password());
        user.setUserType(request.userType());
        //user.setTenantId(request.tenantId());
        //user.setCompanyId(request.companyId());

        return user;
    }

    public UserResponse toResponse(AppUser user) {

        return UserResponse.builder()
                .id(user.getId())
                .employeeCode(user.getEmployeeCode())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .userType(user.getUserType())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .build();
    }
}

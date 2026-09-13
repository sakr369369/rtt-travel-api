package com.roottronics.travelcore.user.dto.request;

import com.roottronics.travelcore.user.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record CreateUserRequest(

        String employeeCode,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @Email(message = "Invalid email address")
        @NotBlank(message = "Email is required")
        String email,

        @Pattern(
                regexp = "^[6-9]\\d{9}$",
                message = "Invalid mobile number")
        String mobile,

        @NotBlank(message = "Password is required")
        String password,

        @NotNull(message = "User type is required")
        UserType userType

) {
}

package com.roottronics.travelcore.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record UpdateUserRequest(

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @Email(message = "Invalid email")
        String email,

        @Pattern(
                regexp = "^[6-9]\\d{9}$",
                message = "Invalid mobile number")
        String mobile

) {
}

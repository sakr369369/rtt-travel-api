package com.roottronics.travelcore.tenant.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateTenantRequest(

        /*@NotBlank(message = "Tenant Code is required")
        @Size(max = 20, message = "Tenant Code must not exceed 20 characters")
        String tenantCode,*/

        //Long tenantId,

        String employeeCode,

        @NotBlank(message = "Tenant Name is required")
        @Size(max = 150, message = "Tenant Name must not exceed 150 characters")
        String tenantName,

        @NotBlank(message = "Contact Person is required")
        @Size(max = 100, message = "Contact Person must not exceed 100 characters")
        String contactPerson,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 150, message = "Email must not exceed 150 characters")
        String email,

        @NotBlank(message = "Mobile Number is required")
        @Pattern(
                regexp = "^[0-9]{10,15}$",
                message = "Mobile Number must contain between 10 and 15 digits"
        )
        String mobile,

        @Size(max = 100, message = "Country must not exceed 100 characters")
        String country,

        @Size(max = 100, message = "State must not exceed 100 characters")
        String state,

        @Size(max = 100, message = "City must not exceed 100 characters")
        String city,

        @Size(max = 500, message = "Address must not exceed 500 characters")
        String address

) {
}
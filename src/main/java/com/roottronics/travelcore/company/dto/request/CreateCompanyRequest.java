package com.roottronics.travelcore.company.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateCompanyRequest(

        @NotBlank(message = "Company Code is required")
        @Size(max = 20, message = "Company Code must not exceed 20 characters")
        String companyCode,

        @NotBlank(message = "Company Name is required")
        @Size(max = 150, message = "Company Name must not exceed 150 characters")
        String companyName,

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
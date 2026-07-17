package com.roottronics.travelcore.candidate.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateCandidateRequest(

        @NotNull(message = "Company Id is required")
        Long companyId,

        @NotBlank(message = "Candidate Code is required")
        @Size(max = 30, message = "Candidate Code must not exceed 30 characters")
        String candidateCode,

        @NotBlank(message = "First Name is required")
        @Size(max = 100, message = "First Name must not exceed 100 characters")
        String firstName,

        @Size(max = 100, message = "Last Name must not exceed 100 characters")
        String lastName,

        @NotBlank(message = "Gender is required")
        @Pattern(
                regexp = "^(MALE|FEMALE|OTHER)$",
                message = "Gender must be MALE, FEMALE or OTHER"
        )
        String gender,

        @NotNull(message = "Date of Birth is required")
        LocalDate dateOfBirth,

        @NotBlank(message = "Passport Number is required")
        @Size(max = 50, message = "Passport Number must not exceed 50 characters")
        String passportNumber,

        @NotNull(message = "Passport Expiry Date is required")
        LocalDate passportExpiryDate,

        @NotBlank(message = "Nationality is required")
        @Size(max = 100, message = "Nationality must not exceed 100 characters")
        String nationality,

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

        @Size(max = 500, message = "Address must not exceed 500 characters")
        String address

) {
}
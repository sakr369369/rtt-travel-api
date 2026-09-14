package com.roottronics.travelcore.candidate.entity;

import com.roottronics.travelcore.common.entity.BaseEntity;
import com.roottronics.travelcore.common.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "candidates",
        uniqueConstraints = {

                @UniqueConstraint(
                        name = "uk_candidate_code",
                        columnNames = {"candidate_code"}
                ),

                @UniqueConstraint(
                        name = "uk_candidate_passport",
                        columnNames = {"passport_number"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate extends BaseEntity {


    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "candidate_code", nullable = false, length = 30)
    private String candidateCode;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "gender", nullable = false, length = 20)
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "passport_number", nullable = false, length = 50)
    private String passportNumber;

    @Column(name = "passport_expiry_date")
    private LocalDate passportExpiryDate;

    @Column(name = "nationality", length = 100)
    private String nationality;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "mobile", length = 20)
    private String mobile;

    @Column(name = "address", length = 500)
    private String address;

}

package com.roottronics.travelcore.candidate.mapper;

import com.roottronics.travelcore.candidate.dto.request.CreateCandidateRequest;
import com.roottronics.travelcore.candidate.dto.response.CandidateResponse;
import com.roottronics.travelcore.candidate.entity.Candidate;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    /**
     * Request -> Entity
     */
    public Candidate toEntity(CreateCandidateRequest request) {

        Candidate candidate = new Candidate();

        candidate.setCompanyId(request.companyId());

        candidate.setCandidateCode(request.candidateCode().trim());

        candidate.setFirstName(request.firstName().trim());

        candidate.setLastName(request.lastName());

        candidate.setGender(request.gender());

        candidate.setDateOfBirth(request.dateOfBirth());

        candidate.setPassportNumber(request.passportNumber().trim());

        candidate.setPassportExpiryDate(request.passportExpiryDate());

        candidate.setNationality(request.nationality());

        candidate.setEmail(request.email().trim().toLowerCase());

        candidate.setMobile(request.mobile().trim());

        candidate.setAddress(request.address());

        return candidate;
    }

    /**
     * Entity -> Response
     */
    public CandidateResponse toResponse(Candidate candidate) {

        return CandidateResponse.builder()

                .id(candidate.getId())

                .candidateCode(candidate.getCandidateCode())

                .companyId(candidate.getCompanyId())


                .firstName(candidate.getFirstName())

                .lastName(candidate.getLastName())

                .gender(candidate.getGender())

                .dateOfBirth(candidate.getDateOfBirth())

                .passportNumber(candidate.getPassportNumber())

                .passportExpiryDate(candidate.getPassportExpiryDate())

                .nationality(candidate.getNationality())

                .email(candidate.getEmail())

                .mobile(candidate.getMobile())

                .address(candidate.getAddress())

                .status(candidate.getStatus())

                .createdDate(candidate.getCreatedAt())

                .updatedDate(candidate.getUpdatedAt())

                .build();
    }

}

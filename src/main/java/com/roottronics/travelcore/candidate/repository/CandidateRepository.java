package com.roottronics.travelcore.candidate.repository;

import com.roottronics.travelcore.candidate.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Optional<Candidate> findByCandidateCodeAndTenantId(
            String candidateCode,
            Long tenantId
    );

    Optional<Candidate> findByPassportNumberAndTenantId(
            String passportNumber,
            Long tenantId
    );

    Optional<Candidate> findByEmailAndTenantId(
            String email,
            Long tenantId
    );

    Optional<Candidate> findByMobileAndTenantId(
            String mobile,
            Long tenantId
    );

}
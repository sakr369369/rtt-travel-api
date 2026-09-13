package com.roottronics.travelcore.candidate.repository;

import com.roottronics.travelcore.candidate.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Optional<Candidate> findByCandidateCode(String candidateCode);

    Optional<Candidate> findByPassportNumber(String passportNumber);

    Optional<Candidate> findByEmail(String email);

    Optional<Candidate> findByMobile(String mobile);

}

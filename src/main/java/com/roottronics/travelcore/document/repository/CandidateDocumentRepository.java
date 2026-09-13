package com.roottronics.travelcore.document.repository;

import com.roottronics.travelcore.document.entity.CandidateDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateDocumentRepository
        extends JpaRepository<CandidateDocument, Long> {

    Optional<CandidateDocument> findByCandidateIdAndDocumentTypeId(
            Long id,
            Long documentTypeId
    );

    List<CandidateDocument> findByCandidateId(Long id);

    boolean existsByCandidateIdAndDocumentTypeId(
            Long id,
            Long documentTypeId
    );

}
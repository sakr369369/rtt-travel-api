package com.roottronics.travelcore.document.repository;

import com.roottronics.travelcore.document.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

    Optional<DocumentType> findByDocumentCode(String documentCode);

    Optional<DocumentType> findByDocumentName(String documentName);

}
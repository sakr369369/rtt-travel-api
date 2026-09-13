package com.roottronics.travelcore.document.service.impl;

import com.roottronics.travelcore.candidate.entity.Candidate;
import com.roottronics.travelcore.candidate.repository.CandidateRepository;
import com.roottronics.travelcore.company.entity.Company;
import com.roottronics.travelcore.company.repository.CompanyRepository;
import com.roottronics.travelcore.exception.BadRequestException;
import com.roottronics.travelcore.document.dto.request.UploadCandidateDocumentRequest;
import com.roottronics.travelcore.document.dto.response.CandidateDocumentResponse;
import com.roottronics.travelcore.document.entity.CandidateDocument;
import com.roottronics.travelcore.document.entity.DocumentType;
import com.roottronics.travelcore.document.mapper.CandidateDocumentMapper;
import com.roottronics.travelcore.document.repository.CandidateDocumentRepository;
import com.roottronics.travelcore.document.repository.DocumentTypeRepository;
import com.roottronics.travelcore.document.service.CandidateDocumentService;
import com.roottronics.travelcore.security.userdetails.CustomUserDetails;
import com.roottronics.travelcore.storage.model.StoredFile;
import com.roottronics.travelcore.storage.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateDocumentServiceImpl
        implements CandidateDocumentService {

    private final CandidateDocumentRepository candidateDocumentRepository;

    private final CandidateRepository candidateRepository;

    private final DocumentTypeRepository documentTypeRepository;

    private final CandidateDocumentMapper candidateDocumentMapper;

    private final CompanyRepository companyRepository;

    private final FileStorageService fileStorageService;

    @Override
    public CandidateDocumentResponse uploadDocument(
            UploadCandidateDocumentRequest request,
            MultipartFile file) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails currentUser =
                (CustomUserDetails) authentication.getPrincipal();


        log.info("========== Upload Candidate Document ==========");

        log.info("Candidate Id    : {}", request.candidateId());
        log.info("Document Type Id: {}", request.documentTypeId());
        log.info("Document Number : {}", request.documentNumber());
        log.info("Original File   : {}", file.getOriginalFilename());
        log.info("Content Type    : {}", file.getContentType());
        log.info("File Size       : {} bytes", file.getSize());


        if (file == null || file.isEmpty()) {
            throw new BadRequestException("Document file is required.");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null ||
                !(fileName.toLowerCase().endsWith(".pdf")
                        || fileName.toLowerCase().endsWith(".jpg")
                        || fileName.toLowerCase().endsWith(".jpeg")
                        || fileName.toLowerCase().endsWith(".png"))) {

            throw new BadRequestException(
                    "Only PDF, JPG, JPEG and PNG files are allowed.");
        }

        long maxSize = 20 * 1024 * 1024; // 20 MB

        if (file.getSize() > maxSize) {
            throw new BadRequestException(
                    "Maximum file size is 20 MB.");
        }

        Candidate candidate = candidateRepository.findById(request.candidateId())
                .orElseThrow(() ->
                        new BadRequestException("Candidate not found."));


        DocumentType documentType =
                documentTypeRepository.findById(request.documentTypeId())
                        .orElseThrow(() ->
                                new BadRequestException("Document Type not found."));

        if (candidateDocumentRepository.existsByCandidateIdAndDocumentTypeId(
                candidate.getId(),
                documentType.getId())) {

            throw new BadRequestException(
                    documentType.getDocumentName() + " already uploaded.");
        }

        Company company = companyRepository.findById(candidate.getCompanyId())
                .orElseThrow(() ->
                        new BadRequestException("Company not found."));

        log.info("Candidate Id  : {}", candidate.getId());
        log.info("Company Id      : {}", company.getId());
        log.info("Document Type   : {}", documentType.getDocumentName());

        StoredFile storedFile =
                fileStorageService.storeFile(
                        file,
                        company.getId(),
                        candidate.getId(),
                        documentType.getDocumentCode()
                );

        log.info("========== File Stored ==========");
        log.info("Stored File Name : {}", storedFile.fileName());
        log.info("Stored File Path : {}", storedFile.filePath());
        log.info("Stored File Size : {}", storedFile.fileSize());
        log.info("Stored MIME Type : {}", storedFile.contentType());

        CandidateDocument document =
                candidateDocumentMapper.toEntity(request);


        document.setCompany(company);

        document.setCandidate(candidate);

        document.setDocumentType(documentType);

        document.setFileName(storedFile.fileName());

        document.setFilePath(storedFile.filePath());

        document.setFileSize(storedFile.fileSize());

        document.setContentType(storedFile.contentType());

        log.info("========== Entity Before Save ==========");
        log.info("File Name    : {}", document.getFileName());
        log.info("File Path    : {}", document.getFilePath());
        log.info("File Size    : {}", document.getFileSize());
        log.info("Content Type : {}", document.getContentType());

        CandidateDocument savedDocument =
                candidateDocumentRepository.save(document);

        System.out.println("================================");
        System.out.println("Stored File Name : " + storedFile.fileName());
        System.out.println("Stored File Path : " + storedFile.filePath());

        System.out.println("Entity File Name : " + document.getFileName());
        System.out.println("Entity File Path : " + document.getFilePath());
        System.out.println("================================");

        log.info("========== Saved Successfully ==========");
        log.info("Document Id : {}", savedDocument.getId());
        log.info("========================================");

        return candidateDocumentMapper.toResponse(savedDocument);

    }

}
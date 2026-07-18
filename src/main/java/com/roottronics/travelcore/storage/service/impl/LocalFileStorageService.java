package com.roottronics.travelcore.storage.service.impl;

import com.roottronics.travelcore.storage.config.FileStorageProperties;
import com.roottronics.travelcore.storage.model.StoredFile;
import com.roottronics.travelcore.storage.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocalFileStorageService
        implements FileStorageService {

    private final FileStorageProperties fileStorageProperties;

    @Override
    public StoredFile storeFile(
            MultipartFile file,
            Long tenantId,
            Long companyId,
            Long candidateId,
            String documentType) {

        try {

            String originalFilename =
                    StringUtils.cleanPath(file.getOriginalFilename());

            String extension = "";

            int index = originalFilename.lastIndexOf(".");

            if (index > 0) {
                extension = originalFilename.substring(index);
            }

            String uniqueFileName =
                    documentType.toUpperCase()
                            + "_"
                            + LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                            + "_"
                            + UUID.randomUUID().toString().substring(0, 8)
                            + extension;

            Path uploadPath = Paths.get(
                    fileStorageProperties.getUploadDir(),
                    "tenant-" + tenantId,
                    "company-" + companyId,
                    "candidate-" + candidateId,
                    documentType.toUpperCase()
            );

            Files.createDirectories(uploadPath);

            Path targetLocation =
                    uploadPath.resolve(uniqueFileName);

            Files.copy(
                    file.getInputStream(),
                    targetLocation,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return new StoredFile(
                    uniqueFileName,
                    targetLocation.toString(),
                    file.getSize(),
                    file.getContentType()
            );

        } catch (IOException ex) {

            throw new RuntimeException(
                    "Unable to store file.",
                    ex
            );

        }

    }

}
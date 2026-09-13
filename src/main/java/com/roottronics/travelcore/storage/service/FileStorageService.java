package com.roottronics.travelcore.storage.service;

import com.roottronics.travelcore.storage.model.StoredFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    /**
     * Stores file locally.
     *
     * @return Stored absolute file path
     */
    StoredFile storeFile(
            MultipartFile file,
            Long companyId,
            Long candidateId,
            String documentType);

}
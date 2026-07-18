package com.roottronics.travelcore.storage.model;

public record StoredFile(

        String fileName,

        String filePath,

        Long fileSize,

        String contentType

) {
}
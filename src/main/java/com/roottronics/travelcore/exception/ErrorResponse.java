package com.roottronics.travelcore.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        boolean success,

        int status,

        String error,

        String message,

        LocalDateTime timestamp
) {
}

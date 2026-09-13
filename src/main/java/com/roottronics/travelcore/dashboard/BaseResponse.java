package com.roottronics.travelcore.dashboard;

public record BaseResponse<T>(
        boolean success,
        String message,
        T data
) {
}
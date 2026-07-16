package com.roottronics.travelcore.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private boolean success;

    private String message;

    private T data;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    public static <T> BaseResponse<T> success(String message, T data) {

        return BaseResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();

    }

    public static <T> BaseResponse<T> failure(String message) {

        return BaseResponse.<T>builder()
                .success(false)
                .message(message)
                .build();

    }

}

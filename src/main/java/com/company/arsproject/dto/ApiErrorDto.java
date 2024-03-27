package com.company.arsproject.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErrorDto {
    private String message;
    private Object developerMessage;
    private String path;
    private int status;
    private LocalDateTime timestamp;

    public ApiErrorDto(String message, Object developerMessage, String path, int status) {
        this.message = message;
        this.path = path;
        this.developerMessage = developerMessage;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return """
                *message : %s*
                *errorPath : %s*
                *errorCode : %s*
                *developerMessage* : `%s%n`
                """.formatted(message, path, status, developerMessage);
    }
}

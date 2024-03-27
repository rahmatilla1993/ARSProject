package com.company.arsproject.handlers;

import com.company.arsproject.dto.ApiErrorDto;
import com.company.arsproject.exception.ExistsException;
import com.company.arsproject.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public HttpEntity<ApiErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String path = request.getRequestURI();
        String message = "Not valid input";
        Map<String, List<String>> devMessage = getErrorMap(e);
        ApiErrorDto errorDto = new ApiErrorDto(message, devMessage, path, HttpStatus.BAD_REQUEST.value());
        log.error("Validation error: {}", errorDto);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler({ExistsException.class})
    public HttpEntity<ApiErrorDto> handleExistsException(ExistsException e, HttpServletRequest request) {
        log.error("Username already exists: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiErrorDto.builder()
                        .timestamp(LocalDateTime.now())
                        .message(e.getMessage())
                        .path(request.getRequestURI())
                        .status(HttpStatus.CONFLICT.value())
                        .build());
    }

    @ExceptionHandler({NotFoundException.class})
    public HttpEntity<ApiErrorDto> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        log.error("Not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiErrorDto.builder()
                        .timestamp(LocalDateTime.now())
                        .message(e.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .build());
    }

    private static Map<String, List<String>> getErrorMap(MethodArgumentNotValidException e) {
        Map<String, List<String>> devMessage = new HashMap<>();
        for (FieldError error : e.getFieldErrors()) {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            devMessage.compute(fieldName, (s, values) -> {
                if (!Objects.isNull(values))
                    values.add(errorMessage);

                else
                    values = new ArrayList<>(Collections.singleton(errorMessage));
                return values;
            });
        }
        return devMessage;
    }
}

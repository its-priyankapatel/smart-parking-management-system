package com.smart_parking.smart_parking.exception;

import com.smart_parking.smart_parking.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse>handleValidationException(IllegalArgumentException exception)
    {
        ExceptionResponse response= new ExceptionResponse(false, exception.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}

package com.mantrivenkatraj.workbench;

import com.mantrivenkatraj.workbench.dtos.ApiError;
import com.mantrivenkatraj.workbench.exceptions.ApplicationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🔹 Handles ALL custom business exceptions
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiError> handleApplicationException(ApplicationException ex) {

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new ApiError(
                        ex.getStatusCode(),
                        ex.getMessage()
                ));
    }

    // 🔹 Validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {

        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(400, errors));
    }

    // 🔹 DB constraint fallback
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDbConflict() {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiError(
                        409,
                        "Username or Email already exists"
                ));
    }

    // 🔹 Final safety net
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
//
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(new ApiError(
//                        500,
//                        "Internal server error"
//                ));
//    }
}

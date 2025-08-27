package com.imanol.media_tracker.exception;

import com.imanol.media_tracker.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * ========================= 400 BAD REQUEST =========================
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        ErrorResponse errorResponse = new ErrorResponse("Validation failed", errors);
        log.error("Validation errors: {}", errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String detail = String.format(
                "El valor '%s' no es válido para el parámetro '%s'. Se esperaba un número.",
                ex.getValue(), ex.getName()
        );

        ErrorResponse error = ErrorResponse.builder()
                .message("Parámetro inválido")
                .details(Collections.singletonList(detail))
                .build();
        log.error("Type mismatch: {}", detail);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * ========================= 401 UNAUTHORIZED =========================
     */
    @ExceptionHandler(ObjectNotExistsException.class)
    public ResponseEntity<ErrorResponse> handleObjectNotExistsException(ObjectNotExistsException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .message("Recurso no encontrado")
                .details(Collections.singletonList(ex.getMessage()))
                .build();
        log.error("Not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }


    /**
     * ========================= 401 UNAUTHORIZED =========================
     */
    @ExceptionHandler(CustomLoginException.class)
    public ResponseEntity<ErrorResponse> handleLoginException(CustomLoginException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .message("Autenticación fallida")
                .details(Collections.singletonList(ex.getMessage()))
                .build();
        log.error("Unauthorized: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }


    /**
     * ========================= 409 CONFLICT =========================
     */

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .message("Conflicto")
                .details(Collections.singletonList(ex.getMessage()))
                .build();
        log.error("Conflict: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /**
     * ========================= 500 INTERNAL SERVER ERROR =========================
     */

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseException(DataAccessException ex) {
        String detail = "Error en la base de datos: " + ex.getMostSpecificCause().getMessage();
        ErrorResponse error = ErrorResponse.builder()
                .message("Error interno del servidor")
                .details(Collections.singletonList(detail))
                .build();
        log.error("Database error: {}", detail, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        String detail = ex.getMessage() != null ? ex.getMessage() : ex.toString();
        ErrorResponse error = ErrorResponse.builder()
                .message("Ha ocurrido un error inesperado")
                .details(Collections.singletonList(detail))
                .build();
        log.error("Unexpected error: {}", detail, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

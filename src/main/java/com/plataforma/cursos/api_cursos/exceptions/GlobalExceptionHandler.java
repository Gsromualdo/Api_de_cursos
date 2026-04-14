package com.plataforma.cursos.api_cursos.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RestErrorMessage> handleRuntimeException(RuntimeException ex) {
        RestErrorMessage error = new RestErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<RestErrorMessage>> handleValidationErrors(MethodArgumentNotValidException ex) {

        List<RestErrorMessage> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(fieldError -> new RestErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            "Campo" + fieldError.getField() + "': " + fieldError.getDefaultMessage()
        ))
        .toList();

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<RestErrorMessage> handleCourseAlreadyExists(CourseAlreadyExistsException ex) {
        RestErrorMessage error = new RestErrorMessage(
            HttpStatus.CONFLICT.value(),
            ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }


    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleCourseNotFound(CourseNotFoundException ex) {
        RestErrorMessage error = new RestErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
}

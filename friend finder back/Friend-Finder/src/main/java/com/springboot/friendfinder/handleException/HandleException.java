package com.springboot.friendfinder.handleException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class HandleException {
    private final MessageImplementation messageImplementation;

    public HandleException(MessageImplementation messageImplementation) {
        this.messageImplementation = messageImplementation;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(messageImplementation.getMessage(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponse>> handleIllegalArgument(
            MethodArgumentNotValidException e) {

        return ResponseEntity.badRequest().body(
                e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error ->
                                messageImplementation.getMessage(
                                        error.getDefaultMessage() // 👈 المهم
                                )
                        )
                        .toList()
        );
    }



}

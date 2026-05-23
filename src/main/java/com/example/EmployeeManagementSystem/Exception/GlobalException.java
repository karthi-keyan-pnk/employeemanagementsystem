package com.example.EmployeeManagementSystem.Exception;

import com.example.EmployeeManagementSystem.DTO.Errorcode;
import com.example.EmployeeManagementSystem.DTO.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice

public class GlobalException {

    @ExceptionHandler(MyOwnException.class)
    public ResponseEntity<Errorcode> myException(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Errorcode error= new Errorcode(
                LocalDateTime.now(),
                e.getMessage(),
                status.value(),
                status.name(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error,status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Errorcode> handleValidationException(
            MethodArgumentNotValidException e,
            HttpServletRequest request)
    {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String message = e.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        Errorcode error = new Errorcode(
                LocalDateTime.now(),
                message,
                status.value(),
                status.name(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Errorcode> handleException(Exception e, HttpServletRequest request) {

    HttpStatus status = HttpStatus.NOT_FOUND;
        Errorcode error= new Errorcode(
                LocalDateTime.now(),
                e.getMessage(),
                status.value(),
                status.name(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error,status);
    }


}

package com.kakaopay.assignment.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 공통 에러처리
 */
@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ResponseAPI> exceptionHandler(HttpServletRequest request, final Exception e){
        return new ResponseEntity<>(new ResponseAPI("fail",e.getMessage(),LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.example.demo.exception;


import org.apache.coyote.BadRequestException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;
import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.STACK_TRACE;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    private final ErrorAttributes errorAttributes;

    public CustomExceptionHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex,
                                                                WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
                                                                       WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(BadRequestException ex,
                                                                  WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        List<ValidationExceptionDto> collect =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(ValidationExceptionDto::new)
                        .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(collect);
    }


    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        return new HashMap<>(errorAttributes.getErrorAttributes(webRequest,
                ErrorAttributeOptions.of(STACK_TRACE, MESSAGE)));
    }

}

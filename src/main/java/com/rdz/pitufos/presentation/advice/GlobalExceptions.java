package com.rdz.pitufos.presentation.advice;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.rdz.pitufos.business.exception.*;
import com.rdz.pitufos.common.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptions extends ResponseEntityExceptionHandler {

    private static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact administration";
    private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
    private static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration";
    private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorMessage> accountDisabledException() {
        ErrorMessage errorMessage = new ErrorMessage(new Date(),HttpStatus.BAD_REQUEST,ACCOUNT_DISABLED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorMessage> badCredentialsException() {
        ErrorMessage errorMessage = new ErrorMessage(new Date(),HttpStatus.BAD_REQUEST,INCORRECT_CREDENTIALS);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> accessDeniedException() {
        ErrorMessage errorMessage = new ErrorMessage(new Date(),HttpStatus.FORBIDDEN,NOT_ENOUGH_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ErrorMessage> lockedException() {
        ErrorMessage errorMessage = new ErrorMessage(new Date(),HttpStatus.UNAUTHORIZED,ACCOUNT_LOCKED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorMessage> tokenExpiredException(TokenExpiredException exception) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(),HttpStatus.UNAUTHORIZED,exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }



    @ExceptionHandler(EmailExistException.class)
    ResponseEntity<ErrorMessage> emailExistException(EmailExistException exception){
        ErrorMessage errorMessage = new ErrorMessage(new Date(),HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    ResponseEntity<ErrorMessage> emailNotFoundException(EmailNotFoundException exception){
        ErrorMessage errorMessage = new ErrorMessage(new Date(),HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(UsernameExistException.class)
    ResponseEntity<ErrorMessage> usernameExistException(UsernameExistException exception){
        ErrorMessage errorMessage = new ErrorMessage(new Date(),HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    ResponseEntity<ErrorMessage> usernameNotFoundException(UsernameNotFoundException exception){
        ErrorMessage errorMessage = new ErrorMessage(new Date(),HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(ResourceNotFoundExcpetion.class)
    ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundExcpetion exception){
        ErrorMessage errorMessage = new ErrorMessage(new Date(),HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(BadValidationException.class)
    ResponseEntity<ErrorMessage> badValidationException(BadValidationException exception){
        ErrorMessage errorMessage = new ErrorMessage(new Date(),HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(ResourceExistOnBillException.class)
    ResponseEntity<ErrorMessage> resourceExistOnBillException(ResourceExistOnBillException exception){
        ErrorMessage errorMessage = new ErrorMessage(new Date(),HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(StockException.class)
    ResponseEntity<ErrorMessage> stockException(StockException exception){
        ErrorMessage errorMessage = new ErrorMessage(new Date(),HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}

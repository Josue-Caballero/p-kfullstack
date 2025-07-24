
package com.kruger.kdevfull.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ NoHandlerFoundException.class })
    public ResponseEntity<ErrorMessage> noHandlerFoundException(NoHandlerFoundException e) {
        
        List<String> errors = Arrays.asList(e.getMessage());
        String messageError = "Resource not found";
        return errorResponse(HttpStatus.NOT_FOUND.value(),  messageError, errors);
    
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ InternalAuthenticationServiceException.class })
    public ResponseEntity<ErrorMessage> authenticationException(InternalAuthenticationServiceException e) {
        
        String messageError = "Bad credentials";
        return errorResponse(HttpStatus.BAD_REQUEST.value(),  messageError, null);
    
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ ExpiredJwtException.class })
    public ResponseEntity<ErrorMessage> authorizationException(ExpiredJwtException e) {
        
        String messageError = "Unathorized";
        return errorResponse(HttpStatus.FORBIDDEN.value(),  messageError, null);
    
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
	
        List<String> errors = e.getBindingResult()
            .getFieldErrors().stream().map(x -> x.getDefaultMessage())
            .collect(Collectors.toList());
        String messageError = "Error validating request fields";
    
        return errorResponse(HttpStatus.BAD_REQUEST.value(),  messageError, errors);
	
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ 
        RuntimeException.class, 
        Exception.class 
    })
    public ResponseEntity<ErrorMessage> runtimeExceptionHandler(Exception e){
        
        return errorResponse(HttpStatus.BAD_REQUEST.value(),  e.getMessage(), null);
    
    }
    
    private ResponseEntity<ErrorMessage> errorResponse(
        int status, String messageException, List<String> errorList)  {

        if(errorList == null){
            errorList = Arrays.asList(messageException); }
		ErrorMessage errorDetails = new ErrorMessage(
            status, new Date(), messageException , errorList);
		
        return new ResponseEntity<ErrorMessage>(errorDetails, HttpStatus.valueOf(status));
	
    }

}

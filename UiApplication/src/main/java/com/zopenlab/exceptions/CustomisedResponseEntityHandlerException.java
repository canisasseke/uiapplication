/*package com.zopenlab.exceptions;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomisedResponseEntityHandlerException extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		 List<String> list= ex.getBindingResult()
			        .getAllErrors().stream()
			        .map(ObjectError::getDefaultMessage)
			        .collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	protected ResponseEntity<Object> handlerAccountBadCredentialsException(UsernameNotFoundException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(LockedException.class)
	protected ResponseEntity<Object> handlerAccountLockedException(LockedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(AuthenticationException.class)
	protected ResponseEntity<Object> handlerAuthException(AuthenticationException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
}
*/
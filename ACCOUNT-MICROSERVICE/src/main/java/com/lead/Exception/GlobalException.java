package com.lead.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.lead.DTO.ErrorResponse;

@RestControllerAdvice
public class GlobalException {

	// Handle your custom exception
	@ExceptionHandler(AccountIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAccountNotFound(AccountIdNotFoundException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(ex.getMessage());
		error.setPath(request.getDescription(false));
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	// Handle all other exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(ex.getMessage());
		error.setPath(request.getDescription(false));
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public void handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> fieldErrors = new HashMap<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			fieldErrors.put(error.getField(), error.getDefaultMessage());
		}
	}

}

package com.aster.app.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {
	// When the passed argument is not accepted
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> myExpHandler(IllegalArgumentException ie) {
		System.out.println("inside myHandler method...");
		return new ResponseEntity<String>(ie.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	//When the validation criteria is not met, this error is thrown and messages from messages.properties file is displayed
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Error> validationHandler(MethodArgumentNotValidException ex){
		Error error = new Error();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage("Validation Error");
		String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
		error.setDetails(msg);
		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
	}
	
	//While making the api calls via postman, when wrong handler is passed, this exception is thrown
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Error> notFoundExceptionHandler(NoHandlerFoundException nfe,WebRequest req) {
		Error err=new Error(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);
		}

	//This is for any other general exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> myExceptionHandler(Exception e, WebRequest req) {
		Error err = new Error(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<Error>(err, HttpStatus.BAD_REQUEST);
	}
}

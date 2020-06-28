package com.hennangadelha.workshopmongo.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hennangadelha.workshopmongo.services.excepetion.ObjectNotFoundException;

// anotaçao para trtar posssiveis erros nas requisiçoes
@ControllerAdvice 
public class ResourceExceptionHandler {

	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(),
				"Não encontrado", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
	}
	
}

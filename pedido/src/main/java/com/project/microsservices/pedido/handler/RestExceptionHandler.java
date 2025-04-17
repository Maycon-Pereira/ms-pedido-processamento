package com.project.microsservices.pedido.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.project.microsservices.pedido.model.exception.BadRequestException;
import com.project.microsservices.pedido.model.exception.ErrorMessage;

@ControllerAdvice
public class RestExceptionHandler {

	public ResponseEntity<?> handlerBadRequest(BadRequestException ex) {
		ErrorMessage erro = new ErrorMessage("Bad Request", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<> (erro, HttpStatus.BAD_REQUEST);
	}
	
	
}

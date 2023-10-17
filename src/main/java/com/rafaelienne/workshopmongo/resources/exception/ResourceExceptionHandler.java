package com.rafaelienne.workshopmongo.resources.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rafaelienne.workshopmongo.services.exception.ObjectNotFoundException;

/*A annotation @ControllerAdvice é para interceptar as excessões que ocorrerem para que o objeto
ResourceExceptionHandler realize um possível tratamento*/
@ControllerAdvice
/*A classe ResourceExceptionHandler é para dar o tratamento manual ao erro*/
public class ResourceExceptionHandler {
	
	/*A annotation @ExceptionHandler intercepta o ObjectNotFoundException*/
	@ExceptionHandler(ObjectNotFoundException.class)
	/*A classe objectNotFound trata o ObjectNotFoundException*/
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		String error = "Object not found";
		/*O status NOT_FOUND é o erro 404*/
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		/*O status permite retornar uma resposta com código personalizado*/
		return ResponseEntity.status(status).body(err);

	}
	

}

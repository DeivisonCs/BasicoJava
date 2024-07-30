package ifba.gsort.partohumano.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> EntityNotFoundExceptionHandler(EntityNotFoundException e, HttpServletRequest request) {
    	StandardError error = StandardError.builder()
	    		.timestamp(Instant.now())
	    		.status(HttpStatus.NOT_FOUND.value())
	    		.error("Entity Not Found")
	    		.message(e.getMessage())
	    		.path(request.getRequestURI())
	    		.build();
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e, HttpServletRequest request) {  	
    	StandardError error = StandardError.builder()
        		.timestamp(Instant.now())
        		.status(HttpStatus.BAD_REQUEST.value())
        		.error("Json Não Serializável")
        		.message(e.getMessage())
        		.path(request.getRequestURI())
        		.build();
    	
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardError> ResponseStatusExceptionHandler(ResponseStatusException e, HttpServletRequest request) {   	
    	StandardError error = StandardError.builder()
        		.timestamp(Instant.now())
        		.status(e.getStatusCode().value())
        		.error(e.getReason())
        		.message(e.getMessage())
        		.path(request.getRequestURI())
        		.build();
    	
    	return ResponseEntity.status(e.getStatusCode()).body(error);
    }
    
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardError> NullPointerExceptionHandler(NullPointerException e, HttpServletRequest request) {
    	StandardError error = StandardError.builder()
        		.timestamp(Instant.now())
        		.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        		.error("Null sei o que aconteceu")
        		.message(e.getMessage())
        		.path(request.getRequestURI())
        		.build();
    	
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

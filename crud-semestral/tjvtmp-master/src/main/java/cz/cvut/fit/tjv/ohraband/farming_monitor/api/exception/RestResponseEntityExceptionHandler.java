package cz.cvut.fit.tjv.ohraband.farming_monitor.api.exception;

import cz.cvut.fit.tjv.ohraband.farming_monitor.business.EntityStateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {


    //Handle exception from business
    @ExceptionHandler(value = {EntityStateException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Entity not unique";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    //Handle newly created REST API exception
    @ExceptionHandler(NoEntityFoundException.class)
    public ResponseEntity<Object> handleNodataFoundException(
            NoEntityFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "No entity found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}

package library.api.exceptionhandle;

import library.api.exception.ApiRequestSuccessfull;
import library.api.exception.ApiRequestException;
import library.api.exceptionhandle.responceEntity.EntityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
class ApiHandleError extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleException(ApiRequestException e){

        EntityResponse responseEntity = new EntityResponse<>(500,
                new Timestamp(System.currentTimeMillis()),e.getMessage(),null);

        return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {ApiRequestSuccessfull.class})
    public ResponseEntity<Object> handleSuccessful(ApiRequestSuccessfull e){
        EntityResponse responseEntity = new EntityResponse(200,
                new Timestamp(System.currentTimeMillis()),e.getMessage(),null);

        return new ResponseEntity<>(responseEntity, HttpStatus.resolve(200));
    }

}

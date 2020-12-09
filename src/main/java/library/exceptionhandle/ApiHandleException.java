package library.exceptionhandle;

import library.api.responceEntity.EntityResponse;
import library.exceptionhandle.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;

@RestControllerAdvice
class ApiHandleException extends ResponseEntityExceptionHandler {
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

    @ExceptionHandler(value = {ExistException.class})
    public ResponseEntity<Object> handleExist(ExistException e){
        return createResEntity("Exist Error",200);
    }


    public ResponseEntity<Object> createResEntity(String message,int status){
        EntityResponse responseEntity = new EntityResponse(1,
                new Timestamp(System.currentTimeMillis()),message,null);
        return new ResponseEntity<>(responseEntity, HttpStatus.resolve(status));
    }

}

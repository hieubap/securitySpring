package library.exceptionhandle;

import library.exceptionhandle.exception.*;
import library.api.responceEntity.EntityResponse;
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

    @ExceptionHandler(value = {ExistException.class})
    public ResponseEntity<Object> handleExist(ExistException e){
        return createResEntity("Exist Error",200);
    }

    @ExceptionHandler(value = PasswordNotCorrectException.class)
    public ResponseEntity<Object> handlePasswordIncorrect(){
        return createResEntity("your password is not correct",200);
    }
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFound(UsernameNotFoundException e){
        return createResEntity("username" + e.getMessage() + "is not found",200);
    }



    public ResponseEntity<Object> createResEntity(String message,int status){
        EntityResponse responseEntity = new EntityResponse(1,
                new Timestamp(System.currentTimeMillis()),message,null);
        return new ResponseEntity<>(responseEntity, HttpStatus.resolve(status));
    }

}

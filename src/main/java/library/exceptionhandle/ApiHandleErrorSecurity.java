package library.exceptionhandle;

import library.api.responceEntity.EntityResponse;
import library.exceptionhandle.exception.AuthenticationExceptionThrow;
import library.exceptionhandle.exception.PasswordNotCorrectException;
import library.exceptionhandle.exception.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@RestControllerAdvice
public class ApiHandleErrorSecurity extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { AuthenticationExceptionThrow.class})
    public ResponseEntity<Object> handleUsernameNotFound(){
        System.out.println("not active");

        EntityResponse entity = new EntityResponse(1,
                new Timestamp(System.currentTimeMillis()),"error",null);

        return new ResponseEntity<>(entity,HttpStatus.resolve(500));
//        return createResEntity("username" + e.getMessage() + "is not found",200);
    }
    @ExceptionHandler(value = PasswordNotCorrectException.class)
    public ResponseEntity<Object> handlePasswordIncorrect(){
        return createResEntity("your password is not correct",200);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public @ResponseBody
    ResponseEntity<Object> handleUsernameNotFound(UsernameNotFoundException e, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    throws IOException {
        httpServletResponse.sendError(404,"active");
        return createResEntity("api active",200);
//        BasicAuthenticationFilter
    }

    public ResponseEntity<Object> createResEntity(String message, int status){
        EntityResponse responseEntity = new EntityResponse(1,
                new Timestamp(System.currentTimeMillis()),message,null);
        return new ResponseEntity<>(responseEntity, HttpStatus.resolve(status));
    }
}

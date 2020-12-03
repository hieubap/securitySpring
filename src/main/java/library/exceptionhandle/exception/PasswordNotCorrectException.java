package library.exceptionhandle.exception;

public class PasswordNotCorrectException extends RuntimeException{
    public PasswordNotCorrectException(){
        super("password is not correct");
    }
}

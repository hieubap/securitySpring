package library.exceptionhandle.exception;

import org.springframework.security.access.AccessDeniedException;

public class AuthenticationExceptionThrow extends AccessDeniedException {

    public AuthenticationExceptionThrow(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthenticationExceptionThrow(String msg) {
        super(msg);
    }
}

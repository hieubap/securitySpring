package library.exceptionhandle.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameNotFoundException extends AuthenticationException {
    public UsernameNotFoundException(String username) {
        super("username :" + username + " is not found" +
                "check your username");
    }
}

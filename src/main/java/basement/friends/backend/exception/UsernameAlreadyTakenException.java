package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class UsernameAlreadyTakenException extends BusinessException{
    public UsernameAlreadyTakenException() {
        super(HttpStatus.FORBIDDEN.value(), "Username is already taken");
    }
}

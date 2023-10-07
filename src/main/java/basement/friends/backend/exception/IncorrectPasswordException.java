package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class IncorrectPasswordException extends BusinessException {
    public IncorrectPasswordException() {
        super(HttpStatus.BAD_REQUEST.value(), "Provided password is incorrect");
    }
}

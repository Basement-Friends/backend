package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class EmailExistsException extends BusinessException {
    public EmailExistsException() {
        super(HttpStatus.NOT_ACCEPTABLE.value(), "Account for provided email already exists");
    }
}
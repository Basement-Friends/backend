package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class RequestAlreadyExistsException extends BusinessException{
    public RequestAlreadyExistsException() {
        super(HttpStatus.FORBIDDEN.value(), "Request already exists");
    }
}

package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class FailedToConnectException extends BusinessException{
    public FailedToConnectException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }
}
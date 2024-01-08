package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class RequestNotFoundException extends BusinessException{
    public RequestNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Request not found");
    }
}


package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends BusinessException{
    public EmailNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Provided email not found");
    }
}

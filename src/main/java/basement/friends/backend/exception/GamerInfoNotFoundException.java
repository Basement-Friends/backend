package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class GamerInfoNotFoundException extends BusinessException{
    public GamerInfoNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Extended user information for that user don't exist");
    }
}

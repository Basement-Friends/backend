package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class ChatNotFoundException extends BusinessException{
    public ChatNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Chat not found");
    }
}

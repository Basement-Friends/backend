package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class UserIdNotFoundException extends BusinessException{
    public UserIdNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Given userId doesn't exist");
    }
}

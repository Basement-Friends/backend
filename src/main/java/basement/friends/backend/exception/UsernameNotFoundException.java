package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class UsernameNotFoundException extends BusinessException{
    public UsernameNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "User for given username doesn't exist");
    }
}

package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class RankAlreadyExistsException extends BusinessException {
    public RankAlreadyExistsException() {
        super(HttpStatus.FORBIDDEN.value(), "Rank by provided nam already exists");
    }
}

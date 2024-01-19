package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class RankNotFoundException extends BusinessException{
    public RankNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Rank not found");
    }
}

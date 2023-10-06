package basement.friends.backend.exception;

import lombok.Getter;

@Getter
public class BusinessException  extends RuntimeException {
    int status;
    String message;

    public BusinessException(int status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }
}

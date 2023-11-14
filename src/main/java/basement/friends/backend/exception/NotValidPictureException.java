package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class NotValidPictureException extends BusinessException {
    public NotValidPictureException() {
        super(HttpStatus.BAD_REQUEST.value(), "Provided file has the wrong extension. Please only provide files with PNG and JPEG extension");
    }
}

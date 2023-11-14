package basement.friends.backend.exception;

import org.springframework.http.HttpStatus;

public class PictureNotFoundException extends BusinessException{
    public PictureNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Picture does not exist!");
    }
}

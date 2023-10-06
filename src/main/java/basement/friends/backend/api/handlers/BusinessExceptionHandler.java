package basement.friends.backend.api.handlers;

import basement.friends.backend.api.handlers.DTO.ErrorResponse;
import basement.friends.backend.exception.EmailNotFoundException;
import basement.friends.backend.exception.UserIdNotFoundException;
import basement.friends.backend.exception.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse usernameNotFoundException(UsernameNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse userIdNotFoundException(UserIdNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse emailNotFoundException(EmailNotFoundException exception) {
        return new ErrorResponse(exception);
    }
}

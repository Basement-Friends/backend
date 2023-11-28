package basement.friends.backend.api.handlers;

import basement.friends.backend.api.handlers.DTO.ErrorResponse;
import basement.friends.backend.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse usernameNotFoundExceptionResponse(UsernameNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse userIdNotFoundExceptionResponse(UserIdNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse emailNotFoundExceptionResponse(EmailNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse emailExistsExceptionResponse(EmailExistsException exception) {
        return new ErrorResponse(exception);
    }
    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse incorrectPasswordExceptionResponse(IncorrectPasswordException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(PictureNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse pictureNotFoundExceptionResponse(PictureNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(ChatNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse chatNotFoundExceptionResponse(ChatNotFoundException exception) {
        return new ErrorResponse(exception);
    }

}

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
    public ErrorResponse getUsernameNotFoundExceptionResponse(UsernameNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse getUserIdNotFoundExceptionResponse(UserIdNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse getEmailNotFoundExceptionResponse(EmailNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse getEmailExistsExceptionResponse(EmailExistsException exception) {
        return new ErrorResponse(exception);
    }
    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse getIncorrectPasswordExceptionResponse(IncorrectPasswordException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(PictureNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse getPictureNotFoundExceptionResponse(PictureNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(ChatNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse getChatNotFoundExceptionResponse(ChatNotFoundException exception) {
        return new ErrorResponse(exception);
    }
    @ExceptionHandler(GamerInfoNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse getGamerInfoNotFoundExceptionResponse(GamerInfoNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse getUsernameAlreadyTakenExceptionResponse(UsernameAlreadyTakenException exception) {
        return new ErrorResponse(exception);
    }

}

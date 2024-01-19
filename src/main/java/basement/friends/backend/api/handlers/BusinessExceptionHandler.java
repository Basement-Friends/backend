package basement.friends.backend.api.handlers;

import basement.friends.backend.api.handlers.DTO.ErrorResponse;
import basement.friends.backend.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse handleAnyException(AccessDeniedException ex, WebRequest request) {
        return new ErrorResponse(new BusinessException(HttpStatus.FORBIDDEN.value(), ex.getMessage()));
    }

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

    @ExceptionHandler(NotValidPictureException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse getNotValidPictureExceptionResponse(NotValidPictureException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(FailedToConnectException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse getFailedToConnectExceptionResponse(FailedToConnectException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(RequestNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse getRequestNotFoundExceptionResponse(RequestNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(RankNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse getRankNotFoundExceptionResponse(RankNotFoundException exception) {
        return new ErrorResponse(exception);
    }

}

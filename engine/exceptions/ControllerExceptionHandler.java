package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<CustomErrorMessage> handleQuizNotFound(QuizNotFoundException e,
                                                                 WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage(HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UnauthorizedQuizDeleteException.class)
    public ResponseEntity<CustomErrorMessage> handleUnauthorizedQuizDelete(UnauthorizedQuizDeleteException e,
                                                                 WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage(403,
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(body,HttpStatus.valueOf(403));
    }
}

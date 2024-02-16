package engine.exceptions;

public class UnauthorizedQuizDeleteException extends RuntimeException {
    public UnauthorizedQuizDeleteException(String message) {
        super(message);
    }
}

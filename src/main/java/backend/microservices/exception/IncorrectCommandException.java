package backend.microservices.exception;

public class IncorrectCommandException extends RuntimeException{
    public IncorrectCommandException(String message) {
        super(message);
    }
}

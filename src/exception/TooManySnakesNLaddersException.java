package exception;

public class TooManySnakesNLaddersException extends RuntimeException{
    private String message;
    public TooManySnakesNLaddersException(String message) {
        super(message);
        this.message = message;
    }    
}

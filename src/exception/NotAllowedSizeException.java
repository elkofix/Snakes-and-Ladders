package exception;

public class NotAllowedSizeException extends RuntimeException{
    private String message;
    public NotAllowedSizeException(String message) {
        super(message);
        this.message = message;
    }    
}

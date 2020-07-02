package exceptions;

public class NotesUnavailableForAmountException extends RuntimeException{
    public NotesUnavailableForAmountException(String message) {
        super(message);
    }
}

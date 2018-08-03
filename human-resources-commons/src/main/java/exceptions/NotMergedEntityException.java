package exceptions;

public class NotMergedEntityException extends Exception {

    private static final long serialVersionUID = 1L;

    public NotMergedEntityException(String message) {
        super(message);
    }
}

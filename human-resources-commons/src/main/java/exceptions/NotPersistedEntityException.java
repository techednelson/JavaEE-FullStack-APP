package exceptions;

public class NotPersistedEntityException extends Exception {

    private static final long serialVersionUID = 1L;

    public NotPersistedEntityException(String message) {
        super(message);

    }
}

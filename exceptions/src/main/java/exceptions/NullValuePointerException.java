package exceptions;

import java.io.Serializable;

public class NullValuePointerException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public NullValuePointerException(String message) {
        super(message);
    }
}

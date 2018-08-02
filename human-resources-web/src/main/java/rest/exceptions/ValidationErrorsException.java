package rest.exceptions;

import java.io.Serializable;

public class ValidationErrorsException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public ValidationErrorsException(String message) {

        super(message);
    }
}

package rest.exceptions;

import java.io.Serializable;

public class InvalidLocationException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public InvalidLocationException(String message) {

        super(message);
    }
}

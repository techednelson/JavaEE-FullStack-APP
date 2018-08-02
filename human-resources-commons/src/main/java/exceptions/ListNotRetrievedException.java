package exceptions;

import java.io.Serializable;

public class ListNotRetrievedException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public ListNotRetrievedException(String message) { super(message); }
}

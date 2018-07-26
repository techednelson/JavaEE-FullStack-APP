package exceptions.mapper;

import exceptions.ListNotRetrievedException;
import exceptions.NullValuePointerException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        if(e instanceof NullValuePointerException) {
            return Response.status(Response.Status.SEE_OTHER).entity(e.getMessage()).build();
        } else if(e instanceof ListNotRetrievedException) {
            return Response.status(Response.Status.SEE_OTHER).entity(e.getMessage()).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}

package rest.resources;

import exceptions.NotCreateNamedQueryException;
import exceptions.NotMergedEntityException;
import exceptions.NotPersistedEntityException;
import model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rest.boundary.EmployeeBoundary;
import rest.exceptions.InvalidLocationException;
import rest.exceptions.ValidationErrorsException;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
public class EmployeeResource {

    private Logger logger = LogManager.getLogger(EmployeeResource.class.getName());

    @EJB
    private EmployeeBoundary boundary;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerEmployee(Employee employee) throws ValidationErrorsException,
                                                                InvalidLocationException,
                                                                NotPersistedEntityException {

        logger.info("Starting web service call registerEmployee");
        if(employee == null) {
            throw new BadRequestException("Data received was incorrect, please try again");
        } else {
            Employee newEmployee = boundary.registerEmployee(employee);
            return Response.ok(newEmployee).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() throws NotCreateNamedQueryException {
        logger.info("Starting web service call getAllEmployees");
        List<Employee> employeeList = boundary.getAllEmployees();

        return Response.ok(employeeList).build();
    }

    @GET
    @Path("{id:\\d{1,4}}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") Integer id) {
        logger.info("Starting web service call getEmployeeById");
        Employee employee = boundary.getEmployeeById(id);

        if(employee == null) {
            throw new NotFoundException("Could not find employee " + id);
        }

        return Response.ok(employee).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee) throws NotMergedEntityException {
        logger.info("Starting web service call updaterEmployee");
        if(employee == null) {

            throw new BadRequestException("Data received was incorrect, please try again");

        } else {
            boundary.updateEmployee(employee);
            return Response.ok(employee).build();
        }
    }

    @DELETE
    @Path("{id:\\d{1,4}}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response deleteEmployee(@PathParam("id") Integer id) {
        logger.info("Starting web service call deleteEmployee");
        boundary.deleteEmployee(id);

        return Response.ok().build();
    }

}

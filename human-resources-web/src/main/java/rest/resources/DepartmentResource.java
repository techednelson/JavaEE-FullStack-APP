package rest.resources;

import model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rest.boundary.DepartmentBoundary;
import rest.exceptions.ValidationErrorsException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/departments")
public class DepartmentResource {

    private final Logger logger = LogManager.getLogger(DepartmentResource.class.getName());

    @Inject
    private DepartmentBoundary boundary;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDepartment(Department department) throws ValidationErrorsException {

        logger.info("Starting web service call createDepartment");
        if(department == null) {

            throw new BadRequestException("Data received was incorrect, please try again");

        } else {

            Department newDepartment = boundary.createDepartment(department);

            return Response.ok(newDepartment).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {

        logger.info("Starting web service call getAllDepartments");
        List<Department> departmentList = boundary.getAllDepartments();

        return Response.ok(departmentList).build();
    }

    @GET
    @Path("{id:\\d{1,4}}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentById(@PathParam("id") Integer id) {

        logger.info("Starting web service call getDepartmentById");
        Department department = boundary.getDepartmentById(id);

        if (department == null) {
            throw new NotFoundException("Could not find department " + id);
        }

            return Response.ok(department).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDepartment(Department department) {

        logger.info("Starting web service call updateDepartment");

        if (department == null) {

            throw new BadRequestException("Data received was incorrect, please try again");
        }

        boundary.updateDepartment(department.getId());

        return Response.ok().build();
    }

    @DELETE
    @Path("{id:\\d{1,4}}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response deleteDepartment(@PathParam("id") Integer id) {

        logger.info("Starting web service call deleteDepartment");
        boundary.deleteDepartment(id);

        return Response.ok().build();
    }

}

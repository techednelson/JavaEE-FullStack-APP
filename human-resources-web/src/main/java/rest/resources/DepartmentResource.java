package rest.resources;

import exceptions.ListNotRetrievedException;
import exceptions.NullValuePointerException;
import model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rest.boundary.DepartmentBoundary;

import service.DepartmentService;

import javax.ejb.EJB;
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

    @EJB
    private DepartmentService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDepartment(Department department) throws NullValuePointerException {

        logger.info("Starting JAX-RS createMail call from DepartmentResource");

        if(department == null) {
            logger.error("There was an error with data received, department is equal to null");
            throw new NullValuePointerException("There was an error with data received, department is equal to null");
        } else {
            Department newDepartment = boundary.performDepartmentValidation(department);
            if (newDepartment == null) {
                logger.error("Department could not be created due to an internal error");
                throw new InternalServerErrorException("There was a problem with our system, please try later");
            }

            return Response.ok(newDepartment).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments()  throws ListNotRetrievedException {

        logger.info("Starting JAX-RS getAllDepartments call from DepartmentResource");

        List<Department> departmentList = service.listDepartments();
        if(departmentList == null) {
            logger.error("Department list could not be retrieved, there was an internal error");
            throw new ListNotRetrievedException("Department list could not be retrieved, there was an internal error");
        }

        return Response.ok(departmentList).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentById(@PathParam("id") Integer id) throws NullValuePointerException {

        logger.info("Starting JAX-RS getDepartmentById call from DepartmentResource");

        if(id == null) {
            logger.error("There was an error with the id, it's value is empty or has a bad format");
            throw new NullValuePointerException("There was an error with the id, it's value is empty or has a bad format");
        } else {
            Department department = service.findDepartmentById(id);
            if (department == null) {
                logger.error("Department could not be found");
                throw new NotFoundException("Could not find department " + id);
            }

            return Response.ok(department).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDepartment(Department department) throws NullValuePointerException {

        logger.info("Starting JAX-RS updateDepartment call from DepartmentResource");

        if(department == null) {
            logger.error("There was an error with data received, department is equal to null");
            throw new NullValuePointerException("There was an error with data received, department is equal to null");
        } else {
            Department current = service.findDepartmentById(department.getId());
            if (current == null) {
                logger.error("Department could not be found in order to be updated");
                throw new NotFoundException("Could not find department " + department.getId());
            }

            service.updateDepartment(department);

            return Response.ok().build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response deleteDepartment(@PathParam("id") Integer id) throws NullValuePointerException {

        logger.info("Starting JAX-RS deleteDepartment call from DepartmentResource");

        if(id == null) {
            logger.error("There was an error with the id, it's value is empty or has a bad format");
            throw new NullValuePointerException("There was an error with the id, it's value is empty or has a bad format");
        } else {
            Department current = service.findDepartmentById(id);
            if (current == null) {
                logger.error("Department could not be found in order to be removed");
                throw new NotFoundException("Could not find department " + id);
            }

            service.deleteDepartment(id);

            return Response.ok().build();
        }
    }

}

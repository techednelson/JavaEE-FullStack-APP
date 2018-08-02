package rest.resources;

import exceptions.ListNotRetrievedException;
import exceptions.NullValuePointerException;
import jms.MailProducerJMS;
import model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rest.boundary.EmployeeBoundary;
import service.EmployeeService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
public class EmployeeResource {

    private Logger logger = LogManager.getLogger(EmployeeResource.class.getName());

    @Inject
    private EmployeeBoundary boundary;

    @EJB
    private EmployeeService service;

    @EJB
    private MailProducerJMS mailProducerJMS;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerEmployee(Employee employee) throws NullValuePointerException {

        logger.info("Starting JAX-RS registerEmployee call from EmployeeResource");

        if(employee == null) {
            logger.error("There was an error with data received, Employee is equal to null");
            throw new NullValuePointerException("There was an error with data received, Employee is equal to null");
        } else {
            Employee newEmployee = boundary.performEmployeeValidation(employee);
            if (newEmployee == null) {
                logger.error("Employee could not be created due to an internal error");
                throw new InternalServerErrorException("There was a problem with our system, please try later");
            }

            mailProducerJMS.sendMessage("Employee created");

            return Response.ok(newEmployee).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() throws ListNotRetrievedException {

        logger.info("Starting JAX-RS getAllEmployees call from EmployeeResource");

        List<Employee> employeeList = service.listEmployees();
        if(employeeList == null) {
            logger.error("Employee list could not be retrieved, there was an internal error");
            throw new ListNotRetrievedException("Employee list could not be retrieved, there was an internal error");
        }

        return Response.ok(employeeList).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") Integer id) throws NullValuePointerException {

        logger.info("Starting JAX-RS getEmployeeById call from EmployeeResource");

        if(id == null) {
            logger.error("There was an error with the id, it's value is empty or has a bad format");
            throw new NullValuePointerException("There was an error with the id, it's value is empty or has a bad format");
        } else {
            Employee employee = service.findEmployeeById(id);
            if(employee == null) {
                logger.error("Employee could not be found");
                throw new NotFoundException("Could not find employee " + id);
            }

            return Response.ok(employee).build();
        }
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee) throws NullValuePointerException {

        logger.info("Starting JAX-RS updaterEmployee call from EmployeeResource");

        if(employee == null) {
            logger.error("There was an error with data received, employee is equal to null");
            throw new NullValuePointerException("There was an error with data received, employee is equal to null");
        } else {
            Employee current = service.findEmployeeById(employee.getId());
            if(current == null) {
                logger.error("Employee could not be found in order to be updated");
                throw new NotFoundException("Could not find employee " + employee.getId());
            }

            if(service.updateEmployee(employee)) {
                mailProducerJMS.sendMessage("Employee updated");
            }

            return Response.ok().build();
        }

    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response deleteEmployee(@PathParam("id") Integer id) throws NullValuePointerException {

        logger.info("Starting JAX-RS deleteEmployee call from EmployeeResource");

        if(id == null) {
            logger.error("There was an error with the id, it's value is empty or has a bad format");
            throw new NullValuePointerException("There was an error with the id, it's value is empty or has a bad format");
        } else {
            Employee current = service.findEmployeeById(id);
            if (current == null) {
                logger.error("Employee could not be found in order to be removed");
                throw new NotFoundException("Could not find employee " + id);
            }

            service.deleteEmployee(id);

            return Response.ok().build();
        }
    }

}

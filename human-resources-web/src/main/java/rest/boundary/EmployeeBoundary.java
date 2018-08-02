package rest.boundary;

import model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rest.exceptions.ValidationErrorsException;
import service.EmployeeService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class EmployeeBoundary {

    @EJB
    private EmployeeService service;

    @Inject
    private ResourcesValidator resourcesValidator;

    private Logger logger = LogManager.getLogger(EmployeeBoundary.class.getName());

    public Employee registerEmployee(Employee employee) throws ValidationErrorsException {

        logger.info("Validating employee before executing create employee service");
        String validationErrors =resourcesValidator.validateEntity(employee);

        if(validationErrors != null) {

            throw new ValidationErrorsException(validationErrors);
        }

        service.createEmployee(employee);
        return employee;
    }

    public List<Employee> getAllEmployees() {

        return service.listEmployees();
    }

    public Employee getEmployeeById(Integer id) {

        return service.findEmployeeById(id);
    }

    public boolean updateEmployee(Integer id) {

        return service.updateEmployee(id);
    }

    public void deleteEmployee(Integer id) {

        service.deleteEmployee(id);
    }




}

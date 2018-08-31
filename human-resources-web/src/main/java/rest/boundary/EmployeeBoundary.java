package rest.boundary;

import exceptions.NotCreateNamedQueryException;
import exceptions.NotMergedEntityException;
import exceptions.NotPersistedEntityException;
import jms.MailProducerJMS;
import model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rest.exceptions.InvalidLocationException;
import rest.exceptions.ValidationErrorsException;
import service.EmployeeService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class EmployeeBoundary {

    @EJB
    private EmployeeService service;

    @EJB
    private MailProducerJMS mailProducerJMS;

    @Inject
    private ResourcesValidator resourcesValidator;

    private Logger logger = LogManager.getLogger(EmployeeBoundary.class.getName());

    public Employee registerEmployee(Employee employee) throws ValidationErrorsException, InvalidLocationException, NotPersistedEntityException {

        logger.info("Validating employee before executing create employee service");
        String validationErrors = resourcesValidator.validateEntity(employee);

        if(validationErrors != null) {

            throw new ValidationErrorsException(validationErrors);
        } else if(!resourcesValidator.validateLocation(employee.getAddress().getCountry(),
                employee.getAddress().getCity())) {
            throw new InvalidLocationException("The location is out of range");
        }

        if(service.createEmployee(employee)) {
            logger.info("Sending confirmation message");
            mailProducerJMS.sendMessage("create");
        }

        return employee;
    }

    public List<Employee> getAllEmployees() throws NotCreateNamedQueryException {

        return service.listEmployees();
    }

    public Employee getEmployeeById(Integer id) {

        return service.findEmployeeById(id);
    }

    public void updateEmployee(Employee employee) throws NotMergedEntityException {

        if(service.updateEmployee(employee)) {
            mailProducerJMS.sendMessage("update");
        }
    }

    public void deleteEmployee(Integer id) {
        service.deleteEmployee(id);
    }

}

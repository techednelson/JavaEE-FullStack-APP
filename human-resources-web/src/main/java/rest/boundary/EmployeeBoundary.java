package rest.boundary;

import model.Employee;
import service.EmployeeService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ValidationException;

@Stateless
public class EmployeeBoundary {

    @EJB
    private EmployeeService service;

    @Inject
    private HibernateValidator hibernateValidator;

    public Employee performEmployeeValidation(Employee employee) {

        if(!hibernateValidator.validateEntity(employee).isEmpty()) {
            throw new ValidationException();
        }

        service.createEmployee(employee);
        return employee;
    }
}

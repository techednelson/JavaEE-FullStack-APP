package rest.boundary;

import model.Department;
import model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rest.exceptions.ValidationErrorsException;
import service.DepartmentService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.validation.ValidationException;
import java.util.List;

@Stateless
public class DepartmentBoundary {

    @EJB
    private DepartmentService service;

    @Inject
    private ResourcesValidator resourcesValidator;

    private Logger logger = LogManager.getLogger(DepartmentBoundary.class.getName());


    public Department createDepartment(Department department)  throws ValidationErrorsException {

        logger.info("Validating department before executing create create service");
        String validationErrors =resourcesValidator.validateEntity(department);

        if(validationErrors != null) {

            throw new ValidationErrorsException(validationErrors);
        }

        service.createDepartment(department);
        return department;
    }

    public List<Department> getAllDepartments() {

        return service.listDepartments();
    }

    public Department getDepartmentById(Integer id) {

        return service.findDepartmentById(id);
    }

    public void updateDepartment(Integer id) {

         service.updateDepartment(id);
    }

    public void deleteDepartment(Integer id) {

        service.findDepartmentById(id);
    }
}

package rest.boundary;

import model.Department;
import service.DepartmentService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.validation.ValidationException;

@Stateless
public class DepartmentBoundary {

    @EJB
    private DepartmentService departmentService;

    @Inject
    private HibernateValidator hibernateValidator;


    public Department performDepartmentValidation(Department department)  {

        if(!hibernateValidator.validateEntity(department).isEmpty()) {
            throw new ValidationException();
        }

        departmentService.createDepartment(department);
        return department;
    }
}

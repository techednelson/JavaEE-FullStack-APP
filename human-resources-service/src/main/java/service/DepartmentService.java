package service;

import exceptions.NotCreateNamedQueryException;
import exceptions.NotMergedEntityException;
import exceptions.NotPersistedEntityException;
import model.Department;

import javax.ejb.Local;
import java.util.List;

public interface DepartmentService {

    boolean createDepartment(Department department) throws NotPersistedEntityException;

    List<Department> listDepartments() throws NotCreateNamedQueryException;

    Department findDepartmentById(Integer id);

    boolean updateDepartment(Department department) throws  NotMergedEntityException;
    boolean deleteDepartment(Integer id);

}

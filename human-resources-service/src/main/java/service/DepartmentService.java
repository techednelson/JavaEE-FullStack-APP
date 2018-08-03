package service;

import exceptions.NotCreateNamedQueryException;
import exceptions.NotMergedEntityException;
import exceptions.NotPersistedEntityException;
import model.Department;

import javax.ejb.Local;
import java.util.List;

public interface DepartmentService {

    void createDepartment(Department department) throws NotPersistedEntityException;

    List<Department> listDepartments() throws NotCreateNamedQueryException;

    Department findDepartmentById(Integer id);

    void updateDepartment(Integer id) throws NotMergedEntityException;

    void deleteDepartment(Integer id);

}

package dao;

import exceptions.NotCreateNamedQueryException;
import exceptions.NotMergedEntityException;
import exceptions.NotPersistedEntityException;
import model.Department;

import java.util.List;

public interface DepartmentDAO {

    void createDepartment(Department department) throws NotPersistedEntityException;

    List<Department> listDepartments() throws NotCreateNamedQueryException;

    Department findDepartmentById(Integer id);

    void updateDepartment(Department department) throws NotMergedEntityException;

    void deleteDepartment(Integer id);

}

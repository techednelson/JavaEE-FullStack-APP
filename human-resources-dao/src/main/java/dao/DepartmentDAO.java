package dao;

import exceptions.NotCreateNamedQueryException;
import exceptions.NotMergedEntityException;
import exceptions.NotPersistedEntityException;
import model.Department;

import java.util.List;

public interface DepartmentDAO {

    boolean createDepartment(Department department) throws NotPersistedEntityException;

    List<Department> listDepartments() throws NotCreateNamedQueryException;

    Department findDepartmentById(Integer id);

    boolean updateDepartment(Department department) throws NotMergedEntityException;

    boolean deleteDepartment(Integer id);

}

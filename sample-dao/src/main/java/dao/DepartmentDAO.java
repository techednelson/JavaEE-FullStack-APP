package dao;

import model.Department;

import java.util.List;

public interface DepartmentDAO {

    void createDepartment(Department department);

    List<Department> listDepartments();

    Department findDepartmentById(Integer id);

    void updateDepartment(Department department);

    void deleteDepartment(Integer id);

}

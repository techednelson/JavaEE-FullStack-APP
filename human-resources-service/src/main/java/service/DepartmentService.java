package service;

import model.Department;

import javax.ejb.Local;
import java.util.List;

public interface DepartmentService {

    void createDepartment(Department department);

    List<Department> listDepartments();

    Department findDepartmentById(Integer id);

    void updateDepartment(Integer id);

    void deleteDepartment(Integer id);

}

package dao;

import model.Employee;

import javax.ejb.Local;
import java.util.List;

public interface EmployeeDAO {

    void createEmployee(Employee employee);

    List<Employee> listEmployees();

    Employee findEmployeeById(Integer id);

    boolean updateEmployee(Employee employee);

    void deleteEmployee(Integer id);

}

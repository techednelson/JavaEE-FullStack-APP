package service;

import model.Employee;

import javax.ejb.Local;
import java.util.List;

public interface EmployeeService {

    void createEmployee(Employee employee);

    List<Employee> listEmployees();

    Employee findEmployeeById(Integer id);

    boolean updateEmployee(Integer id);

    void deleteEmployee(Integer id);

}
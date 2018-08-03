package service;

import exceptions.NotCreateNamedQueryException;
import exceptions.NotMergedEntityException;
import exceptions.NotPersistedEntityException;
import model.Employee;

import javax.ejb.Local;
import java.util.List;

public interface EmployeeService {

    void createEmployee(Employee employee) throws NotPersistedEntityException;

    List<Employee> listEmployees() throws NotCreateNamedQueryException;

    Employee findEmployeeById(Integer id);

    boolean updateEmployee(Integer id) throws NotMergedEntityException;

    void deleteEmployee(Integer id);

}

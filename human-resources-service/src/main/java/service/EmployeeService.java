package service;

import exceptions.NotCreateNamedQueryException;
import exceptions.NotMergedEntityException;
import exceptions.NotPersistedEntityException;
import model.Employee;

import javax.ejb.Local;
import java.util.List;

public interface EmployeeService {

    boolean createEmployee(Employee employee) throws NotPersistedEntityException;

    List<Employee> listEmployees() throws NotCreateNamedQueryException;

    Employee findEmployeeById(Integer id);

    boolean updateEmployee(Employee employee) throws NotMergedEntityException;

    boolean deleteEmployee(Integer id);

}

package service;

import dao.EmployeeDAO;
import exceptions.NotCreateNamedQueryException;
import exceptions.NotMergedEntityException;
import exceptions.NotPersistedEntityException;
import model.Employee;

import javax.ejb.*;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Stateless
@Local(EmployeeService.class)
public class EmployeeServiceImpl implements EmployeeService{

    @EJB
    private EmployeeDAO dao;

    @Override
    public  boolean createEmployee(Employee employee) throws NotPersistedEntityException {
        return dao.createEmployee(employee);
    }

    @Override
    public List<Employee> listEmployees() throws NotCreateNamedQueryException {
        return dao.listEmployees();
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return dao.findEmployeeById(id);
    }


    @Override
    public boolean updateEmployee(Employee employee) throws NotMergedEntityException {
        if(employee != null) {
            return dao.updateEmployee(employee);
        } else {
            throw new NotFoundException("Could not find employee ");
        }
    }

    @Override
    public boolean deleteEmployee(Integer id) {
        return dao.deleteEmployee(id);
    }

}

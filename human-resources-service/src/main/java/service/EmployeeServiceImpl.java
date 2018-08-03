package service;

import dao.EmployeeDAO;
import exceptions.NotCreateNamedQueryException;
import exceptions.NotMergedEntityException;
import exceptions.NotPersistedEntityException;
import model.Employee;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Stateless
@Local(EmployeeService.class)
public class EmployeeServiceImpl implements EmployeeService{

    @EJB
    private EmployeeDAO dao;

    @Override
    public  void createEmployee(Employee employee) throws NotPersistedEntityException {

        dao.createEmployee(employee);
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
    public boolean updateEmployee(Integer id) throws NotMergedEntityException {

        Employee employee = findEmployeeById(id);

        if(employee != null) {
            return dao.updateEmployee(employee);
        } else {
            throw new NotFoundException("Could not find employee " + id);
        }
    }

    @Override
    public void deleteEmployee(Integer id) {

        dao.deleteEmployee(id);
    }

}

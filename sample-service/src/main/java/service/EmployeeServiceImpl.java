package service;

import dao.EmployeeDAO;
import model.Employee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class EmployeeServiceImpl implements EmployeeService{

    @EJB
    private EmployeeDAO dao;

    @Override
    public  void createEmployee(Employee employee) {
        dao.createEmployee(employee);
    }

    @Override
    public List<Employee> listEmployees() {

        return dao.listEmployees();
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return dao.findEmployeeById(id);
    }


    @Override
    public boolean updateEmployee(Employee employee) {

        return dao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        dao.deleteEmployee(id);
    }

}

package dao;

import exceptions.NotCreateNamedQueryException;
import exceptions.NotMergedEntityException;
import exceptions.NotPersistedEntityException;
import model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Stateless
@Local(EmployeeDAO.class)
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext(name = "human_resourcesPU")
    EntityManager entityManager;

    private Logger logger = LogManager.getLogger(DepartmentDAOImpl.class.getName());

    @Override
    public boolean createEmployee(Employee employee) throws NotPersistedEntityException {

        try {
            entityManager.persist(employee);
            return true;
        } catch (Exception e) {
            throw new NotPersistedEntityException("Entity Manager failed to persist the employee");
        }
    }


    @Override
    public List<Employee> listEmployees() throws NotCreateNamedQueryException {

        try {
            return entityManager.createNamedQuery("Employee.findAll").getResultList();
        } catch (Exception e) {
            throw new NotCreateNamedQueryException("Entity Manager failed to retrieve the employee list");
        }
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        Employee employee = entityManager.find(Employee.class, id);

        if(employee == null) {
            logger.error("employee not found");
            throw new NotFoundException("employee not found");
        }

        return employee;
    }

    @Override
    public boolean updateEmployee(Employee employee) throws NotMergedEntityException {
        try {
            Employee tempEmployee = findEmployeeById(employee.getId());
            tempEmployee.setFirstName(employee.getFirstName());
            tempEmployee.setLastName(employee.getLastName());
            tempEmployee.setBirthDate(employee.getBirthDate());
            tempEmployee.setAddress(employee.getAddress());
            tempEmployee.setPhoneNumber(employee.getPhoneNumber());
            tempEmployee.setEmail(employee.getEmail());
            tempEmployee.setSalary(employee.getSalary());
            tempEmployee.setDepartment(employee.getDepartment());
            tempEmployee.setJoinDate(employee.getJoinDate());

            return true;
        } catch (Exception e) {
            throw new NotMergedEntityException("Entity Manager failed to merge the updated employee");
        }

    }

    @Override
    public boolean deleteEmployee(Integer id) {
        Employee employee = findEmployeeById(id);

        if (employee == null) {
            logger.error("employee not found");
            throw new NotFoundException("employee not found");
        }

        try {
            entityManager.remove(employee);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}

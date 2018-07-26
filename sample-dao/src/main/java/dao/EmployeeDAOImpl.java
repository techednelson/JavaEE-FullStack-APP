package dao;

import model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Stateless
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext(name = "human_resourcesPU")
    EntityManager entityManager;

    private Logger logger = LogManager.getLogger(DepartmentDAOImpl.class.getName());

    @Override
    public void createEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public List<Employee> listEmployees() {

        return entityManager.createNamedQuery("Employee.findAll").getResultList();
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
    public boolean updateEmployee(Employee employee) {
        try {
            entityManager.merge(employee);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }

    }

    @Override
    public void deleteEmployee(Integer id) {
        Employee employee = findEmployeeById(id);

        if (employee == null) {
            logger.error("employee not found");
            throw new NotFoundException("employee not found");
        }

        entityManager.remove(employee);
    }

}

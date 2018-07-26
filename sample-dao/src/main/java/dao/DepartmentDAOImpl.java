package dao;

import model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;


@Stateless
@Local(DepartmentDAO.class)
public class DepartmentDAOImpl implements  DepartmentDAO {

    @PersistenceContext(name = "human_resourcesPU")
    private EntityManager entityManager;

    private Logger logger = LogManager.getLogger(DepartmentDAOImpl.class.getName());

    @Override
    public void createDepartment(Department department) { entityManager.persist(department); }

    @Override
    public List<Department> listDepartments() {

        return entityManager.createNamedQuery("Department.findAll").getResultList();
    }

    @Override
    public Department findDepartmentById(Integer id) {
        Department department =  entityManager.find(Department.class, id);

        if(department == null) {
            logger.error("department not found");
            throw new NotFoundException("department not found");
        }

        return department;

    }

    @Override
    public void updateDepartment(Department department) {
       entityManager.merge(department);
    }

    @Override
    public void deleteDepartment(Integer id) {
        Department department = findDepartmentById(id);

        if(department == null) {
            logger.error("department not found");
            throw new NotFoundException("department not found");
        }

        entityManager.remove(department);

    }

}

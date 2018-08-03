package service;

import dao.DepartmentDAO;
import exceptions.NotCreateNamedQueryException;
import exceptions.NotMergedEntityException;
import exceptions.NotPersistedEntityException;
import model.Department;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Stateless
@Local(DepartmentService.class)
public class DepartmentServiceImpl implements DepartmentService {

    @EJB
    private DepartmentDAO dao;

    @Override
    public void createDepartment(Department department) throws NotPersistedEntityException {

        dao.createDepartment(department);
    }

    @Override
    public List<Department> listDepartments() throws NotCreateNamedQueryException {

        return dao.listDepartments();
    }

    @Override
    public Department findDepartmentById(Integer id) {

        return dao.findDepartmentById(id);
    }


    @Override
    public void updateDepartment(Integer id) throws NotMergedEntityException {

        Department department = findDepartmentById(id);
        if(department != null) {
            dao.updateDepartment(department);
        } else {
            throw new NotFoundException("Could not find department " + id);
        }
    }

    @Override
    public void deleteDepartment(Integer id) {

        dao.deleteDepartment(id);
    }

}

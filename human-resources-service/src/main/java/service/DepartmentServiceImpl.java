package service;

import dao.DepartmentDAO;
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
    public void createDepartment(Department department) {

        dao.createDepartment(department);
    }

    @Override
    public List<Department> listDepartments() {

        return dao.listDepartments();
    }

    @Override
    public Department findDepartmentById(Integer id) {

        return dao.findDepartmentById(id);
    }


    @Override
    public void updateDepartment(Integer id) {

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

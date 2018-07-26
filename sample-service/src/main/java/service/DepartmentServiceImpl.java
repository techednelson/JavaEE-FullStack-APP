package service;

import dao.DepartmentDAO;
import model.Department;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
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
    public void updateDepartment(Department department) {
        dao.updateDepartment(department);
    }

    @Override
    public void deleteDepartment(Integer id) {
        dao.deleteDepartment(id);
    }

}

package dao;

import model.Address;
import model.Department;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DepartmentDAOImplTest {

    private static Department department1 = new Department();
    private static Department department2 = new Department();

    @Mock
    private EntityManager entityManagerMock;
    @Mock
    private Query queryMock;
    @InjectMocks
    private DepartmentDAOImpl departmentDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        //Department
        Address departmentAddress1 = new Address("ATHENS", "GREECE", "thisseos", 123, "123456");
        department1 = new Department("RESEARCH", departmentAddress1);
        department1.setId(1000);

        //Department updated
        Address departmentAddress2 = new Address("ATHENS", "GREECE", "thisseos", 123, "123456");
        department2 = new Department("INVESTMENT", departmentAddress2);
        department2.setId(2000);
    }

    @Test
    public void createDepartment() throws Exception {
        doNothing().when(entityManagerMock).persist(department1);
        departmentDAO.createDepartment(department1);
        verify(entityManagerMock).persist(department1);
        System.out.println("EntityManager persist method was invoked from DAO layer \n");
    }

    @Test
    public void listDepartments() throws Exception {
        when(queryMock.getResultList()).thenReturn(Arrays.asList(department1, department2));
        when(entityManagerMock.createNamedQuery("Department.findAll")).thenReturn(queryMock);
        List list = departmentDAO.listDepartments();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        verify(entityManagerMock).createNamedQuery("Department.findAll");
        System.out.println("EntityManager createNamedQuery method was invoked from DAO layer \n");
    }

    @Test
    public void findDepartmentById() {
        when(entityManagerMock.find(Department.class, 2000)).thenReturn(department2);
        departmentDAO.findDepartmentById(2000);
        assertEquals("INVESTMENT", department2.getName());
        verify(entityManagerMock).find(Department.class, 2000);
        System.out.println("EntityManager find method was invoked from DAO layer \n");
    }

    @Test
    public void updateDepartment() throws Exception {
        when(entityManagerMock.find(Department.class, 1000)).thenReturn(department1);
        departmentDAO.updateDepartment(department1);
        assertEquals("RESEARCH", department1.getName());
        System.out.println("EntityManager findEmployeeById method was invoked from updateEmployee in DAO layer \n");
    }

    @Test
    public void deleteDepartment() {
        doNothing().when(entityManagerMock).remove(department2);
        when(entityManagerMock.find(Department.class, 2000)).thenReturn(department2);
        departmentDAO.deleteDepartment(2000);
        assertEquals("INVESTMENT", department2.getName());
        System.out.println("EntityManager findEmployeeById method was invoked from deleteEmployee in DAO layer \n");
    }
}
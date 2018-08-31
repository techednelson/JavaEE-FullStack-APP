package service;

import dao.DepartmentDAO;
import model.Address;
import model.Department;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DepartmentServiceImplTest {

    private static Department department1;
    private static Department department2;

    @Mock
    private DepartmentDAO departmentDAOMock;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

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
        when(departmentDAOMock.createDepartment(department1)).thenReturn(true);
        boolean isDepartmentCreated = departmentService.createDepartment(department1);
        assertTrue(isDepartmentCreated);
        verify(departmentDAOMock).createDepartment(department1);
        System.out.println("createDepartment in DAO was invoked from service layer \n");
    }

    @Test
    public void listDepartments() throws Exception {
        when(departmentDAOMock.listDepartments()).thenReturn(Arrays.asList(department1, department2));
        List list = departmentService.listDepartments();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        verify(departmentDAOMock).listDepartments();
        System.out.println("listDepartments in DAO was invoked from service layer \n");
    }

    @Test
    public void findDepartmentById() {
        when(departmentDAOMock.findDepartmentById(2000)).thenReturn(department2);
        departmentService.findDepartmentById(2000);
        assertEquals("INVESTMENT", department2.getName());
        verify(departmentDAOMock).findDepartmentById(2000);
        System.out.println("findDepartmentId in DAO was invoked from service layer \n");
    }

    @Test
    public void updateDepartment() throws Exception {
        when(departmentDAOMock.updateDepartment(department1)).thenReturn(true);
        boolean isDepartmentUpdated = departmentService.updateDepartment(department1);
        assertTrue(isDepartmentUpdated);
        verify(departmentDAOMock).updateDepartment(department1);
        System.out.println("updateDepartment was in DAO was invoked from service layer \n");
    }

    @Test
    public void deleteDepartment() {
        when(departmentDAOMock.deleteDepartment(2000)).thenReturn(true);
        boolean isDepartmentDeleted = departmentService.deleteDepartment(2000);
        assertTrue(isDepartmentDeleted);
        verify(departmentDAOMock).deleteDepartment(2000);
        System.out.println("deleteDepartment was in DAO was invoked from service layer \n");
    }
}
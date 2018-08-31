package service;

import dao.EmployeeDAO;
import model.Employee;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EmployeeServiceImplTest {

    private static Employee employee1 = new Employee();
    private static Employee employee2 = new Employee();

    @Mock
    private EmployeeDAO employeeDAOMock;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createEmployee() throws Exception {
        when(employeeDAOMock.createEmployee(employee1)).thenReturn(true);
        boolean isEmployeeCreated = employeeService.createEmployee(employee1);
        assertTrue(isEmployeeCreated);
        verify(employeeDAOMock).createEmployee(employee1);
        System.out.println("createEmployee in DAO was invoked from service layer \n");
    }

    @Test
    public void listEmployees() throws Exception {
        when(employeeDAOMock.listEmployees()).thenReturn(Arrays.asList(employee1, employee2));
        List list = employeeService.listEmployees();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        verify(employeeDAOMock).listEmployees();
        System.out.println("listEmployees in DAO was invoked from service layer \n");
    }

    @Test
    public void findEmployeeById() {
        employee2.setId(2000);
        employee2.setFirstName("nelson");
        when(employeeDAOMock.findEmployeeById(2000)).thenReturn(employee2);
        employeeService.findEmployeeById(2000);
        assertEquals("nelson", employee2.getFirstName());
        verify(employeeDAOMock).findEmployeeById(2000);
        System.out.println("findEmployeeId in DAO was invoked from service layer \n");
    }

    @Test
    public void updateEmployee() throws Exception {
        when(employeeDAOMock.updateEmployee(employee1)).thenReturn(true);
        boolean isEmployeeUpdated = employeeService.updateEmployee(employee1);
        assertTrue(isEmployeeUpdated);
        verify(employeeDAOMock).updateEmployee(employee1);
        System.out.println("updateEmployee was in DAO was invoked from service layer \n");
    }

    @Test
    public void deleteEmployee() {
        when(employeeDAOMock.deleteEmployee(2000)).thenReturn(true);
        boolean isEmployeeDeleted = employeeService.deleteEmployee(2000);
        assertTrue(isEmployeeDeleted);
        verify(employeeDAOMock).deleteEmployee(2000);
        System.out.println("deleteEmployee was in DAO was invoked from service layer \n");
    }
}
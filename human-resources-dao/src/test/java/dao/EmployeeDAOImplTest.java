package dao;

import model.Employee;
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

public class EmployeeDAOImplTest {

    private static Employee employee1 = new Employee();
    private static Employee employee2 = new Employee();

    @Mock
    private EntityManager entityManagerMock;
    @Mock
    private Query queryMock;
    @InjectMocks
    private EmployeeDAOImpl employeeDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        employee1.setId(1000);
        employee1.setFirstName("rafael");

        employee2.setId(2000);
        employee2.setFirstName("nelson");
    }

    @Test
    public void createEmployee() throws Exception {
        doNothing().when(entityManagerMock).persist(employee1);
        employeeDAO.createEmployee(employee1);
        verify(entityManagerMock).persist(employee1);
        System.out.println("EntityManager persist method was invoked from DAO layer \n");
    }

    @Test
    public void listEmployees() throws Exception {
        when(queryMock.getResultList()).thenReturn(Arrays.asList(employee1, employee2));
        when(entityManagerMock.createNamedQuery("Employee.findAll")).thenReturn(queryMock);
        List list = employeeDAO.listEmployees();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        verify(entityManagerMock).createNamedQuery("Employee.findAll");
        System.out.println("EntityManager createNamedQuery method was invoked from DAO layer \n");
    }

    @Test
    public void findEmployeeById() {
        when(entityManagerMock.find(Employee.class, 2000)).thenReturn(employee2);
        employeeDAO.findEmployeeById(2000);
        assertEquals("nelson", employee2.getFirstName());
        verify(entityManagerMock).find(Employee.class, 2000);
        System.out.println("EntityManager find method was invoked from DAO layer \n");
    }

    @Test
    public void updateEmployee() throws Exception {
        when(entityManagerMock.find(Employee.class, 1000)).thenReturn(employee1);
        employeeDAO.updateEmployee(employee1);
        assertEquals("rafael", employee1.getFirstName());
        System.out.println("EntityManager findEmployeeById method was invoked from updateEmployee in DAO layer \n");
    }

    @Test
    public void deleteEmployee() {
        doNothing().when(entityManagerMock).remove(employee2);
        when(entityManagerMock.find(Employee.class, 2000)).thenReturn(employee2);
        employeeDAO.deleteEmployee(2000);
        assertEquals("nelson", employee2.getFirstName());
        System.out.println("EntityManager findEmployeeById method was invoked from deleteEmployee in DAO layer \n");
    }

}
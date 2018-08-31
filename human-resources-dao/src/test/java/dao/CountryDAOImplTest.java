package dao;

import model.Country;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CountryDAOImplTest {

    private static Country country1;
    private static Country country2;

    @Mock
    private EntityManager entityManagerMock;
    @Mock
    private Query queryMock;
    @InjectMocks
    private CountryDAOImpl countryDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        country1 = new Country("SPAIN", "BARCELONA");
        country2 = new Country("GREECE", "ATEHNS");
    }

    @Test
    public void listCountries() throws Exception {
        when(queryMock.getResultList()).thenReturn(Arrays.asList(country1, country2));
        when(entityManagerMock.createNamedQuery("Country.findAll")).thenReturn(queryMock);
        List list = countryDAO.listCountries();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        verify(entityManagerMock).createNamedQuery("Country.findAll");
        System.out.println("EntityManager createNamedQuery method was invoked from DAO layer \n");
    }
}
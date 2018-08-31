package service;

import dao.CountryDAO;
import model.Country;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CountryServiceImplTest {

    private static Country country1;
    private static Country country2;

    @Mock
    private CountryDAO countryDAOMock;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        country1 = new Country("SPAIN", "BARCELONA");
        country2 = new Country("GREECE", "ATEHNS");
    }

    @Test
    public void listCountries() throws Exception {
        when(countryDAOMock.listCountries()).thenReturn(Arrays.asList(country1, country2));
        List list = countryService.listCountries();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        verify(countryDAOMock).listCountries();
        System.out.println("listCountries in DAO was invoked from service layer \n");
    }
}
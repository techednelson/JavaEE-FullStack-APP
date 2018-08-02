package rest.boundary;

import model.Country;
import service.CountryService;

import javax.ejb.EJB;
import java.util.List;

public class CountryBoundary {

    @EJB
    private CountryService service;

    public List<Country> getAllCountries() {

        return service.listCountries();
    }
}

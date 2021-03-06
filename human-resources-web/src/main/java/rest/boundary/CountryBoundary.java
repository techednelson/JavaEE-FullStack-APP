package rest.boundary;

import exceptions.NotCreateNamedQueryException;
import model.Country;
import service.CountryService;

import javax.ejb.EJB;
import java.util.List;

public class CountryBoundary {

    @EJB
    private CountryService service;

    public List<Country> getAllCountries() throws NotCreateNamedQueryException {

        return service.listCountries();
    }
}

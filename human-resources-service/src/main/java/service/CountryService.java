package service;

import exceptions.NotCreateNamedQueryException;
import model.Country;

import java.util.List;

public interface CountryService {

    List<Country> listCountries() throws NotCreateNamedQueryException;
}

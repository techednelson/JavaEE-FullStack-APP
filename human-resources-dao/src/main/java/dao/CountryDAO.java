package dao;

import exceptions.NotCreateNamedQueryException;
import model.Country;

import java.util.List;

public interface CountryDAO {

    List<Country> listCountries() throws NotCreateNamedQueryException;
}

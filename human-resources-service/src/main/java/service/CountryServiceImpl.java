package service;

import dao.CountryDAO;
import model.Country;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local(CountryService.class)
public class CountryServiceImpl implements CountryService {

    @EJB
    private CountryDAO dao;

    @Override
    public List<Country> listCountries() {

        return dao.listCountries();
    }

}

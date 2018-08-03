package dao;

import exceptions.NotCreateNamedQueryException;
import model.Country;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Local(CountryDAO.class)
public class CountryDAOImpl implements CountryDAO {

    @PersistenceContext(name = "human_resourcesPU")
    private EntityManager entityManager;

    @Override
    public List<Country> listCountries() throws NotCreateNamedQueryException {

        try {
            return entityManager.createNamedQuery("Country.findAll").getResultList();
        } catch (Exception e) {
            throw new NotCreateNamedQueryException("Entity Manager failed to retrieve the country list");
        }
    }
}

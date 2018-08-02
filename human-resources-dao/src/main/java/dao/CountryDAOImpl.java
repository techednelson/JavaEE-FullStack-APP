package dao;

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
    public List<Country> listCountries() {

        return entityManager.createNamedQuery("Country.findAll").getResultList();
    }
}

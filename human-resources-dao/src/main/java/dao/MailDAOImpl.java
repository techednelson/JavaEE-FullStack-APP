package dao;


import exceptions.NotPersistedEntityException;
import model.Mail;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Local(MailDAO.class)
public class MailDAOImpl implements MailDAO {

    @PersistenceContext(name = "human_resourcesPU")
    private EntityManager entityManager;

    @Override
    public boolean create(Mail mail) throws NotPersistedEntityException {
        try {
            entityManager.persist(mail);
            return true;
        } catch (Exception e) {
            throw new NotPersistedEntityException(
                    "Entity Manager failed persist mail message"
            );
        }
    }
}

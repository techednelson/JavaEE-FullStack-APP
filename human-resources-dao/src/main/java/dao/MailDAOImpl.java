package dao;


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
    public void create(Mail mail) { entityManager.persist(mail); }
}

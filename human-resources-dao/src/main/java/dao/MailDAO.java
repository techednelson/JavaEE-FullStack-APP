package dao;

import exceptions.NotPersistedEntityException;
import model.Mail;

import javax.ejb.Local;

public interface MailDAO {

    boolean create(Mail mail) throws NotPersistedEntityException;

}

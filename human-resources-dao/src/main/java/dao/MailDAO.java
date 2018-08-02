package dao;

import model.Mail;

import javax.ejb.Local;

public interface MailDAO {

    void create(Mail mail);

}

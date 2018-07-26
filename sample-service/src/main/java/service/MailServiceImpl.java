package service;

import dao.MailDAO;
import model.Mail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.TextMessage;
import java.util.Date;

@Stateless
public class MailServiceImpl implements MailService {

    @EJB
    private MailDAO dao;

    private Mail mail;

    private Logger logger = LogManager.getLogger(MailServiceImpl.class.getName());


    @Override
    public void sendMail(TextMessage message) {
        mail = new Mail();

        try {
            mail.setMail_status(message.getText());
            mail.setTime_stamp(new Date().toString());
            dao.create(mail);
            logger.info("mail persistent");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("mail persistent failed");
        }
    }
}

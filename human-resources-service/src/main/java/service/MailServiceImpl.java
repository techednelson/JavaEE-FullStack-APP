package service;

import dao.MailDAO;
import model.Mail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jms.TextMessage;
import java.util.Date;

@Stateless
@Local(MailService.class)
public class MailServiceImpl implements MailService {

    @EJB
    private MailDAO dao;

    private Logger logger = LogManager.getLogger(MailServiceImpl.class.getName());

    @Override
    public void sendMail(TextMessage message) {
        Mail mail = new Mail();

        try {
            if(message.getText().equals("create")) {
                mail.setMailStatus(Mail.MailEnum.CREATE);
            } else {
                mail.setMailStatus(Mail.MailEnum.UPDATE);
            }
            mail.setTimeStamp(new Date());
            dao.create(mail);
            logger.info("mail persistent");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("mail persistent failed");
        }
    }
}

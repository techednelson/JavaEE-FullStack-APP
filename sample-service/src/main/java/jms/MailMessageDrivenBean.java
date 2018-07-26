package jms;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.MailService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/mail") })
public class MailMessageDrivenBean implements MessageListener {

    private final Logger logger = LogManager.getLogger(MailMessageDrivenBean.class.getName());

    @EJB
    private MailService service;

    @Override
    public void onMessage(Message inMessage) {
        TextMessage msg;

        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;
                logger.info("MESSAGE BEAN: Message received: " +
                        msg.getText());
                service.sendMail(msg);
            } else {
                logger.warn("Message of wrong type: " +
                        inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}

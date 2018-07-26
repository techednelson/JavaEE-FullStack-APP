package jms;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.jms.*;

@Singleton
public class MailProducerJMS {

    @Resource(mappedName = "java:jboss/exported/jms/queue/mail")
    private Queue mailQueue;

    @Resource(mappedName = "java:/JmsXA")
    private ConnectionFactory connectionFactory;

    @PostConstruct
    public void sendMessage(String mailMSG) {
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher;
            publisher = session.createProducer(mailQueue);
            connection.start();
            TextMessage message = session.createTextMessage(mailMSG);
            publisher.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
        }
    }

}

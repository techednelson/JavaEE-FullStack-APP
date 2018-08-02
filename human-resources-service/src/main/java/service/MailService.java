package service;

import javax.jms.TextMessage;

public interface MailService {

    void sendMail(TextMessage message);

}

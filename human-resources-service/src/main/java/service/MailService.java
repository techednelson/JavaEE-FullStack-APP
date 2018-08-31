package service;

import javax.jms.TextMessage;

public interface MailService {

    boolean sendMail(TextMessage message);

}

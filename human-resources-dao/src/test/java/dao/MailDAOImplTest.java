package dao;

import model.Mail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class MailDAOImplTest {

    private static Mail mail = new Mail();

    @Mock
    private EntityManager entityManagerMock;

    @InjectMocks
    private MailDAOImpl mailDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() throws Exception {
        doNothing().when(entityManagerMock).persist(mail);
        mailDAO.create(mail);
        verify(entityManagerMock).persist(mail);
        System.out.println("EntityManager persist method was invoked from DAO layer \n");
    }
}
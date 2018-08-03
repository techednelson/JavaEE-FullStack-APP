package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mail")
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "MAIL_SEQ", sequenceName = "MAIL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAIL_SEQ")
    @Column(name = "mail_id")
    private Integer id;

    @Column(name = "mail_status")
    private String mailStatus;

    @Column(name = "time_stamp")
    private String timestamp;

    public Mail() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMailStatus() { return mailStatus; }

    public void setMailStatus(String mail_status) { this.mailStatus = mail_status; }

    public String getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(String time_stamp) {
        this.timestamp = time_stamp;
    }

}

package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "mail")
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Version for optimistic locking
     */
    @Version
    @Column(name = "DBVERSION", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int version;

    @Id
    @SequenceGenerator(name = "MAIL_SEQ", sequenceName = "MAIL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAIL_SEQ")
    @Column(name = "mail_id")
    private Integer id;

    @Column(name = "mail_status")
    private String mailStatus;

    @Column(name = "time_stamp")
    private Date timestamp;

    public Mail() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMailStatus() { return mailStatus; }

    public void setMailStatus(String mail_status) { this.mailStatus = mail_status; }

    public Date getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(Date time_stamp) {
        this.timestamp = time_stamp;
    }

}

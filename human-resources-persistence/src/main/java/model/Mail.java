package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mail")
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "mail_id")
    private Integer id;

    @Column(name = "mail_status")
    private String mail_status;

    @Column(name = "time_stamp")
    private String time_stamp;

    public Mail() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail_status() { return mail_status; }

    public void setMail_status(String mail_status) { this.mail_status = mail_status; }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

}

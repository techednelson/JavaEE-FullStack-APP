package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
})
@Table(name = "country")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Version for optimistic locking
     */
    @Version
    @Column(name = "DBVERSION", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int version;

    @Id
    @SequenceGenerator(name = "COUNTRY_SEQ", sequenceName = "COUNTRY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COUNTRY_SEQ")
    @Column(name="country_id")
    private Integer id;

    @Pattern(regexp = "^\\b\\pL+(?:-\\pL+)*\\b")
    @NotBlank
    @Column(name="country_name")
    private String name;

    @Pattern(regexp = "^\\b\\pL+(?:-\\pL+)*\\b")
    @NotBlank
    @Column(name="city")
    private String city;

    public Country() {}

    public Country(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }
}

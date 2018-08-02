package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "country")
public class Country {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

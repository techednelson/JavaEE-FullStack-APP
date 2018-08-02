package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.*;

@Embeddable
public class Address {

    @Pattern(regexp = "^\\b\\pL+(?:-\\pL+)*\\b")
    @NotBlank
    @Column(name = "city")
    private String city;

    @Pattern(regexp = "^\\b\\pL+(?:-\\pL+)*\\b")
    @NotBlank
    @Column(name = "country")
    private String country;

    @Size(min = 5, max = 35)
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    @NotBlank
    @Column(name = "street")
    private String street;

    @Column(name = "street_number")
    private Integer streetNumber;

    @Size(min = 6, max = 6)
    @Pattern(regexp = "^\\d+")
    @NotBlank
    @Column(name = "zip_code")
    private String zipCode;

    public Address() {}

    public Address(String city, String country, String street, Integer streetNumber, String zipCode) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public Integer getStreetNumber() { return streetNumber; }

    public void setStreetNumber(Integer streetNumber) { this.streetNumber = streetNumber; }

    public String getzipcode() { return zipCode; }

    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
}

package model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "SElECT e FROM Employee e")
})
@Table(name = "employee")
public class Employee  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    @Column(name = "DBVERSION", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int version;

    @Id
    @SequenceGenerator(name = "EMPLOYEE_SEQ", sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ")
    @Column(name = "employee_id")
    private Integer id;

    @Pattern(regexp = "^\\b\\pL+(?:-\\pL+)*\\b")
    @Size(min = 3, max = 15)
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @Pattern(regexp = "^\\b\\pL+(?:-\\pL+)*\\b")
    @Size(min = 3, max = 15)
    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Past
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "birth_date")
    private Date birthDate;

    @Pattern(regexp = "^\\d+")
    @Size(min = 10, max = 10)
    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    @NotBlank
    @Column(name = "email")
    private String email;

    @Digits(integer = 4, fraction = 0)
    @NotBlank
    @Column(name = "salary")
    private Double salary;

    @Past
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "join_date")
    private Date joinDate;

    @Embedded
    @Valid
    private Address address;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    public Employee() { }

    public Employee(String firstName, String lastName, Date birthDate, Address address, String phoneNumber, String email, Double salary, Department department, Date joinDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.joinDate = joinDate;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public Date getBirthDate() { return birthDate; }

    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public Address getAddress() { return address; }

    public void setAddress(Address address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Double getSalary() { return salary; }

    public void setSalary(Double salary) { this.salary = salary; }

    public Department getDepartment() { return department; }

    public void setDepartment(Department department) { this.department = department; }

    public Date getJoinDate() { return joinDate; }

    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }

}

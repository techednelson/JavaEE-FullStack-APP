package model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
})
@Table(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    @Column(name = "DBVERSION", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int version;

    @Id
    @SequenceGenerator(name = "DEPARTMENT_SEQ", sequenceName = "DEPARTMENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPARTMENT_SEQ")
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 15)
    @Column(name = "department_name")
    private String name;

    @Valid
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    public Department() { }

    public Department(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Address getAddress() { return address; }

    public void setAddress(Address address) { this.address = address; }
}

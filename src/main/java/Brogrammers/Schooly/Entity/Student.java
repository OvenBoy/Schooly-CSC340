package Brogrammers.Schooly.Entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studID", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "fName", length = 20)
    private String fName;

    @Size(max = 20)
    @Column(name = "lName", length = 20)
    private String lName;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

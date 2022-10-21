package data.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "instID", nullable = false)
    private Long instID;
    private String fName;
    private String lName;
    private String email;
    private int courseID;

    @Size(max = 20)
    @Column(name = "first_name", length = 20)
    private String firstName;

    @Size(max = 20)
    @Column(name = "last_name", length = 20)
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public Instructor(Long instID, String fName, String lName, String email, int courseID) {
        this.instID = instID;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.courseID = courseID;
    }

    public Instructor() {
    }
    

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public Long getInstID() {
        return instID;
    }

    public void setInstID(Long instID) {
        this.instID = instID;
    }
}

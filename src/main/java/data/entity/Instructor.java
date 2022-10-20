package data.entity;

import javax.persistence.*;

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


    public Instructor(Long instID, String fName, String lName, String email, int courseID) {
        this.instID = instID;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.courseID = courseID;
    }

    public Instructor() {
    }


    @Override
    public String toString() {
        return String.format("Instructor[instID: %ld, fName: %s, lName: %s, email: %s, courseID: %d]",instID, fName, lName, email, courseID);
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

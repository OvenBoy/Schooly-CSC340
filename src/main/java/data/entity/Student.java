package data.entity;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "studID", nullable = false)
    private Long studID;

    private String fName;
    private String lName;
    private String email;

    public Student() {
    }

    public Long getStudID() {
        return studID;
    }

    public void setStudID(Long studID) {
        this.studID = studID;
    }


    public Student(long studID, String fName, String lName, String email) {
        this.studID = studID;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
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
}

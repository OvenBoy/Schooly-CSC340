package Brogrammers.Schooly.Entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @SequenceGenerator(
            name = "instructor_sequence",
            sequenceName = "instructor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "instructor_sequence"
    )
    @Column(name = "instID", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "first_Name", length = 20)
    private String fName;

    @Size(max = 20)
    @Column(name = "last_Name", length = 20)
    private String lName;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "courseID")
    private Integer courseID;

    @Size(max = 20)
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "course_name", length = 20)
    private String courseName;

    public Instructor() {
    }

    public Integer getId() {
        return id;
    }

    public Instructor(String fName, String lName, String email, Integer courseID, String courseName) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

}
package Brogrammers.Schooly.Entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Mapping for DB view
 */
@Entity
@Immutable
@Table(name = "student_course")
@IdClass(StudentCourse.class)
public class StudentCourse implements Serializable {
    @Size(max = 20)
    @Column(name = "f_name", length = 20)
    private String fName;

    @Size(max = 20)
    @Column(name = "l_name", length = 20)
    private String lName;

    @Id
    @NotNull
    @Column(name = "studID", nullable = false)
    private Integer studID;

    @Id
    @NotNull
    @Column(name = "courseID", nullable = false)
    private Integer courseID;

    @Size(max = 20)
    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    public StudentCourse(Integer studID, Integer courseID) {
        this.studID = studID;
        this.courseID = courseID;
    }


    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public Integer getStudID() {
        return studID;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public String getName() {
        return name;
    }

    public StudentCourse() {
    }
}
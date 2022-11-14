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
@Table(name = "grade_studentview")
@IdClass(gradeStudentView.class)
public class gradeStudentView implements Serializable {
    @NotNull
    @Column(name = "courseID", nullable = false)
    @Id
    private Integer courseID;

    @Size(max = 20)
    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    @Id
    private String name;

    @Column(name = "grade")
    private Integer grade;

    @Size(max = 255)
    @Column(name = "studID")
    private Integer studID;

    @Size(max = 20)
    @NotNull
    @Column(name = "courseName", nullable = false, length = 20)
    @Id
    private String courseName;

    public Integer getCourseID() {
        return courseID;
    }

    public String getAssignmentName() {
        return name;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public void setAssignmentName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getStudID() {
        return studID;
    }

    public void setStudID(Integer studID) {
        this.studID = studID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    protected gradeStudentView() {
    }
}
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

    /**
     *
     */
    protected gradeStudentView() {
    }

    //======GETTERS========
    /**
     * get course ID
     *
     * @return course ID
     */
    public Integer getCourseID() {
        return courseID;
    }

    /**
     * get course name
     * @return course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * get student ID
     * @return student ID
     */
    public Integer getStudID() {
        return studID;
    }

    /**
     * get assignment name
     * @return assignment name
     */
    public String getAssignmentName() {
        return name;
    }

    /**
     * get grade 
     * @return grade
     */
    public Integer getGrade() {
        return grade;
    }

    //======SETTERS========
    /**
     * set assignment name
     * @param name assignment name
     */
    public void setAssignmentName(String name) {
        this.name = name;
    }

    /**
     * set Course ID
     * @param courseID course ID
     */
    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    /**
     * set grade
     * @param grade grade of assignment
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * set student ID
     * @param studID student ID
     */
    public void setStudID(Integer studID) {
        this.studID = studID;
    }

    /**
     * set course name
     * @param courseName course name
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}

package Brogrammers.Schooly.Entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Mapping for DB view
 */
@Entity
@Immutable
@Table(name = "assignment_studentview")
@IdClass(AssignmentStudentview.class)
public class AssignmentStudentview implements Serializable {

    @NotNull
    @Column(name = "courseID", nullable = false)
    @Id
    private Integer courseID;

    @Size(max = 20)
    @NotNull
    @Column(name = "assignmentName", nullable = false, length = 20)
    @Id
    private String assignmentName;

    @Column(name = "duedate")
    private LocalDate duedate;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 20)
    @NotNull
    @Column(name = "courseName", nullable = false, length = 20)
    @Id
    private String courseName;

    /**
     * Constructor for this DB view
     */
    protected AssignmentStudentview() {
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
     * get assignment name
     *
     * @return assignment name
     */
    public String getAssignmentName() {
        return assignmentName;
    }

    /**
     * gets due date
     *
     * @return due date
     */
    public LocalDate getDueDate() {
        return duedate;
    }

    /**
     * get description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets course name
     *
     * @return course name
     */
    public String getCourseName() {
        return courseName;
    }

}

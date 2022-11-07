package Brogrammers.Schooly.Entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "assignment")
public class Assignment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private AssignmentId id;

    @MapsId("courseID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "courseID", nullable = false)
    private Course courseID;

    @Column(name = "dueDate")
    private String dueDate;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    public AssignmentId getId() {
        return id;
    }

    public void setId(AssignmentId id) {
        this.id = id;
    }

    public Course getCourseID() {
        return courseID;
    }

    public void setCourseID(Course courseID) {
        this.courseID = courseID;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
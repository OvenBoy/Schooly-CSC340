package Brogrammers.Schooly.Entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "assignment")
@IdClass(Assignment.class)
public class Assignment implements Serializable {

    @Id
    @Column(name = "name")
    private String name;

    @Id
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "courseID")
    private Integer courseID;

    @Column(name = "dueDate")
    private LocalDate dueDate;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    public Assignment(String name, Integer courseID, LocalDate dueDate, String description) {
        this.name = name;
        this.courseID = courseID;
        this.dueDate = dueDate;
        this.description = description;
    }
    public Assignment(String name, LocalDate dueDate, String description) {
        this.name = name;
        this.dueDate = dueDate;
        this.description = description;
    }

    public Assignment() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
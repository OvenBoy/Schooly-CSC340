package Brogrammers.Schooly.Entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @Author Nishant
 * 
 */
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

    /**
     * This constructor creates an assignment object
     * 
     * @param name name of assignment
     * @param courseID ID of course
     * @param dueDate due date
     * @param description description of assignment
     */
    public Assignment(String name, Integer courseID, LocalDate dueDate, String description) {
        this.name = name;
        this.courseID = courseID;
        this.dueDate = dueDate;
        this.description = description;
    }

    /**
     * This constructor creates an assignment object without a description
     * 
     * @param name name of assignment
     * @param dueDate due date
     * @param courseID course ID
     */
    public Assignment(String name, LocalDate dueDate, Integer courseID) {
        this.name = name;
        this.dueDate = dueDate;
        this.courseID = courseID ;
    }

    /**
     *
     */
    public Assignment() {

    }
    
    //=====GETTERS======

    /**
     * gets assignment name
     * @return name of assignment
     */

    public String getName() {
        return name;
    }

    /**
     * gets description
     * @return description of assignment
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets course ID
     * @return course ID
     */
    public Integer getCourseID() {
        return courseID;
    }
    
    /**
     * gets due date
     * @return due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    //=====SETTERS======

    /**
     * sets assignment name
     * @param name name for assignment
     */
    
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets course ID
     * @param courseID course ID
     */
    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    /**
     * sets due date
     * @param dueDate date due
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * sets assignment description
     * @param description description for assignment
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
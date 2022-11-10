package Brogrammers.Schooly.Entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 8
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Column(name = "courseID", nullable = false)
    private Integer courseID;

    @Size(max = 20)
    @Column(name = "name", length = 20)
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public Course() {

    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
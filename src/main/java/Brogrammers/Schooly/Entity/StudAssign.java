package Brogrammers.Schooly.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stud_assign")
@IdClass(StudAssign.class)
public class StudAssign implements Serializable{

    @Id
    private Integer studID;

    @Id
    private Integer courseID;

    @Column(name = "name")
    private String name;

    @Column(name = "grade")
    private Integer grade;

    public StudAssign(Integer studID, Integer courseID, String name, Integer grade) {
        this.studID = studID;
        this.courseID = courseID;
        this.name = name;
        this.grade = grade;
    }
    public StudAssign(String name, Integer grade){
        this.name = name;
        this.grade = grade;
    }

    public StudAssign() {

    }

    public Integer getStudID() {
        return studID;
    }

    public void setStudID(Integer studID) {
        this.studID = studID;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
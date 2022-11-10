package Brogrammers.Schooly.Entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "takes")
@IdClass(Take.class)
public class Take implements Serializable {

    @Id
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "studID", nullable = false)
    private Integer studID;

    @Id
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "courseID", nullable = false)
    private Integer courseID;

    public Take(Integer studID, Integer courseID) {
        this.studID = studID;
        this.courseID = courseID;
    }

    public Take() {
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
}
package Brogrammers.Schooly.Entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AssignmentId implements Serializable {
    private static final long serialVersionUID = 380581151280277392L;
    @NotNull
    @Column(name = "courseID", nullable = false)
    private Integer courseID;

    @Size(max = 20)
    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    private String name;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AssignmentId entity = (AssignmentId) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.courseID, entity.courseID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, courseID);
    }

}
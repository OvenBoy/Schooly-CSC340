package Brogrammers.Schooly.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "courseID", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "name", length = 20)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
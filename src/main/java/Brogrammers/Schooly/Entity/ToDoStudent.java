package Brogrammers.Schooly.Entity;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Mapping for DB view
 */
@Entity
@Table(name = "todo")
public class ToDoStudent {

    @Id
    @SequenceGenerator(
            name = "todo_sequence",
            sequenceName = "todo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "todo_sequence"
    )
    @Column(name = "todoID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "itemName", length = 255)
    private String itemName;

    @Column(name = "Status")
    private boolean status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer ID) {
        this.id = ID;
    }


    public ToDoStudent() {
    }

    public ToDoStudent(String itemName) {
        this.itemName = itemName;
    }

}
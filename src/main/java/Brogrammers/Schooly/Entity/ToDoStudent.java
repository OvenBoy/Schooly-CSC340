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
@Immutable
@Table(name = "todo")
@IdClass(ToDoStudent.class)
public class ToDoStudent implements Serializable {

    @Size(max = 255)
    @Column(name = "itemName", length = 255)
    private String itemName;


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
    @Column(name = "ID", nullable = false)
    private Integer ID;

//    @Column(name = "status", nullable = false)
//    private boolean status;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }

    public ToDoStudent() {

    }

    public ToDoStudent(String itemName) {
        this.itemName = itemName;
//        this.status = status;
    }

}
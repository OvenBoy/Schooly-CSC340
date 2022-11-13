package Brogrammers.Schooly.Entity;

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
    @Column(name = "itemName")
    private String itemName;


    @Id
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "status")
    private boolean status;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ToDoStudent() {

    }

    public ToDoStudent(String itemName, Integer ID, boolean status) {
        this.itemName = itemName;
        this.ID = ID;
        this.status = status;
    }

}
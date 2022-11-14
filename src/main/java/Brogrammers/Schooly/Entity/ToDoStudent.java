package Brogrammers.Schooly.Entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
    @Column(name = "itemName")
    private String itemName;

    @Column(name = "Status")
    private boolean status;
    
    /**
     * Constructor for student to do
     */
    public ToDoStudent() {
    }

    /**
     * constructor to create a to do item
     * @param itemName to do item name
     */
    public ToDoStudent(String itemName) {
        this.itemName = itemName;
    }
    
    //======GETTERS========

    /**
     * get to do ID
     * @return to do ID
     */

    public Integer getId() {
        return id;
    }

    /**
     * get to do item name
     * @return item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * checks if status is 0 or 1 (not done or done)
     * @return 0 or 1 (not done or done respectively)
     */
    public boolean isStatus() {
        return status;
    }
    
    //======SETTERS========

    /**
     * set to do ID
     * @param id to do ID
     */

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * set status 
     * @param status true / false (done / not done) 
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * sets to do item name
     * @param itemName item name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }



}

package Brogrammers.Schooly.views;

import Brogrammers.Schooly.Entity.StudAssign;
import Brogrammers.Schooly.Repository.StudAssignRepository;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

/**
 * This class is used to set up the GradeForm
 *
 * Last Edited: 11/14/2022
 * Edited By: Andrew Van Es
 */
public class GradeForm extends FormLayout {

    // Declaring Variables
    private StudAssign studAssign;
    List<StudAssign> studAssignList;
    Notification notification;
    StudAssignRepository studAssignRepository;
    Binder<StudAssign> bind = new BeanValidationBinder<>(StudAssign.class);

    //Text Fields
    ComboBox<Integer> courseID = new ComboBox<>("Course ID");
    ComboBox<String> name = new ComboBox<>("Assignment Name");
    TextField grade = new TextField("Grade");
    ComboBox<Integer> studID = new ComboBox<>("Student ID");

    //Button Declarations
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    /**
     * This is the main method for the GradeForm
     * @param studAssignRepository this is used to access the studAssign table
     */
    public GradeForm(StudAssignRepository studAssignRepository) {
        addClassName("grade-form");
        this.studAssignRepository = studAssignRepository;

        updateList();
        courseID.setAllowCustomValue(true);
        name.setAllowCustomValue(true);
        studID.setAllowCustomValue(true);
        courseID.setItems(this.studAssignRepository.searchAllCourseID());
        setComboBox();

        add(courseID,name,grade,studID,createButtonsLayout());
        bind.bindInstanceFields(this);
    }

    /**
     * This method is used to create the combo Box for the name and studID
     * within the form
     */
    private void setComboBox() {
        name.setItems(this.studAssignRepository.searchStudAssignByCourseID(courseID.getValue()));
        studID.setItems(this.studAssignRepository.searchStudIDByCourseID(courseID.getValue()));
    }

    /**
     * This method is used to write to the studAssign table
     * @param studAssign
     */
    public void setStudAssign(StudAssign studAssign) {
        this.studAssign = studAssign;
        bind.readBean(studAssign);
    }

    /**
     * This method is used to validate and save and save entries to the table
     */
    private void gradeSave() {
        try {
            bind.writeBean(studAssign);

            // error checking
            if (studAssign.getGrade() == null) {
                notification = Notification.show("Input a Grade");
                return;
            } else if (checkingGrade(studAssign.getStudID(), studAssign.getName()) == 1) {
                notification = Notification.show("Duplicate grade for Student");
                return;
            } else if (studAssign.getCourseID() == null) {
                notification = Notification.show("Select a course ID");
                return;
            } else if (studAssign.getStudID() == null) {
                notification = Notification.show("Select a student ID");
                return;
            } else if (studAssign.getName() == null) {
                notification = Notification.show("Select an Assignment");
                return;
            }

            fireEvent(new SavEvent(this, studAssign));
            setComboBox();
        } catch (ValidationException err) {
            throw new RuntimeException(err);
        }
    }

    /**
     * This method is used to check for duplicate grade entries
     * @param studID student id
     * @param name assingment name
     * @return 1 or 0
     */
    private Integer checkingGrade(Integer studID, String name) {
        for (StudAssign studAssign: studAssignList) {
            if(studAssign.getStudID().equals(studID)) {
                if(studAssign.getName().equals(name)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private void updateList() {
        studAssignList = this.studAssignRepository.findAll();
    }

    /**
     * This method is an event for when the form is closed
     */
    public static class CloseEvent extends GradeFormEvent {
        CloseEvent(GradeForm source) {
            super(source, null);
        }
    }

    /**
     * This method ia an event for when the current form entry is deleted.
     */
    public static class DelEvent extends GradeFormEvent {
        DelEvent(GradeForm source, StudAssign studAssign) {
            super(source, studAssign);
        }
    }

    /**
     * This method is an event for when the current form entry is saved.
     */
    public static class SavEvent extends GradeFormEvent {
        SavEvent(GradeForm source, StudAssign studAssign) {
            super(source, studAssign);
        }
    }

    /**
     * This method is used to create the buttons for the form and
     * assigns their uses
     * @return configuration for the buttons
     */
    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        courseID.addValueChangeListener(e-> setComboBox());
        save.addClickListener(event -> gradeSave());
        delete.addClickListener(event -> fireEvent(new DelEvent(this, studAssign)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    /**
     * This method is used to set up the new events like SavEvent and DelEvent
     */
    public static abstract class GradeFormEvent extends ComponentEvent<GradeForm> {
        private StudAssign studAssign;
        protected GradeFormEvent(GradeForm source, StudAssign studAssign) {
            super(source, false);
            this.studAssign = studAssign;
        }
        public StudAssign getStudAssign() {
            return studAssign;
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
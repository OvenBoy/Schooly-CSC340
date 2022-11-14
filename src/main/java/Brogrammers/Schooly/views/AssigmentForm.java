package Brogrammers.Schooly.views;

import Brogrammers.Schooly.Entity.Assignment;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

/**
 * This class is used to construct the form for the instructor AssignmentView
 *
 * Last Edited: 11/13/2022
 * Edited By: Andrew Van Es
 */
public class AssigmentForm extends FormLayout {

    // Declaring Variables
    private Assignment assignment;
    Notification notification;
    Binder<Assignment> binder = new BeanValidationBinder<>(Assignment.class);

    //Text fields
    TextField courseID = new TextField("Course ID");

    TextField name = new TextField("Assignment Name");
    TextField description = new TextField("Assignment Details");
    DatePicker dueDate = new DatePicker("Due Date");

    //Button declarations
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    /**
     * This method is the main method.
     */
    public AssigmentForm() {
        addClassName("Assignment-form");
        add(name,courseID,description,dueDate,createButtonsLayout());
        binder.bindInstanceFields(this);
    }

    /**
     * This method is used to set new assignment
     * @param assignment used to set the entry into the database
     */
    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
        binder.readBean(assignment);
    }

    /**
     * This method is used to map the buttons with the functions.
     * @return the layout/configuration of the buttons
     */
    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> valAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, assignment)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    /**
     * This method is used for events within the form
     */
    public static abstract class AssignmentFormEvent extends ComponentEvent<AssigmentForm> {
        private Assignment assignment;
        protected AssignmentFormEvent(AssigmentForm source, Assignment assignment) {
            super(source, false);
            this.assignment = assignment;
        }
        public Assignment getAssignment() {
            return assignment;
        }
    }

    /**
     * This method is called for writing new entry for the database.
     */
    private void valAndSave() {
        try {
            binder.writeBean(assignment);

            // error checking
            if(assignment.getCourseID() == null) {
                notification = Notification.show("Course ID cannot be empty");
                return;
            } else if (assignment.getName().isEmpty()) {
                notification = Notification.show("Assignment must have a name");
                return;
            } else if (assignment.getDescription().isEmpty()) {
                notification = Notification.show("Assignment must have a description");
                return;
            } else if (assignment.getDueDate() == null) {
                notification = Notification.show("Assignment must have a due Date");
                return;
            }


            fireEvent(new SaveEvent(this, assignment));
        } catch (ValidationException err) {
            throw new RuntimeException(err);
        }

    }

    /**
     * This is an event for when there is a close event
     * and closes the form without saving
     */
    public static class CloseEvent extends AssignmentFormEvent {
        CloseEvent(AssigmentForm source) {super(source, null); }
    }

    /**
     * This is an event for when an Assignment is deleted by the user
     */
    public static class DeleteEvent extends AssignmentFormEvent {
        DeleteEvent(AssigmentForm source, Assignment assignment) { super(source, assignment); }
    }

    /**
     * This is an event for when someone save a new or edited assignment.
     */
    public static class SaveEvent extends AssignmentFormEvent {
        SaveEvent(AssigmentForm source, Assignment assignment) { super(source, assignment); }
    }

    /**
     * This method is used to set up the listeners.
     * @param eventType
     * @param listener
     * @return
     * @param <T>
     */
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
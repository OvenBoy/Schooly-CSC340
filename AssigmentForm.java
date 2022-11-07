package Brogrammers.Schooly.views;

import Brogrammers.Schooly.Entity.Assignment;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
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

import java.util.List;

public class AssigmentForm extends FormLayout {
    private Assignment assignment;
    Binder<Assignment> binder = new BeanValidationBinder<>(Assignment.class);

    //TextField assignmentID = new TextField("Assignment Name");

    TextField description = new TextField("Assignment Details");
    TextField dueDate = new TextField("Due Date");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public AssigmentForm() {
        addClassName("Assignment-form");
        add(description,dueDate,createButtonsLayout());
        binder.bindInstanceFields(this);
   }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
        binder.readBean(assignment);
    }

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
    private void valAndSave() {
        try {
            binder.writeBean(assignment);
            fireEvent(new SaveEvent(this, assignment));
        } catch (ValidationException err) {
            throw new RuntimeException(err);
        }

    }

    public static class CloseEvent extends AssignmentFormEvent {
        CloseEvent(AssigmentForm source) {super(source, null); }
    }

    public static class DeleteEvent extends AssignmentFormEvent {
        DeleteEvent(AssigmentForm source, Assignment assignment) { super(source, assignment); }
    }

    public static class SaveEvent extends AssignmentFormEvent {
        SaveEvent(AssigmentForm source, Assignment assignment) { super(source, assignment); }
    }
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}



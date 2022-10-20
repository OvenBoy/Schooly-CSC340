package Brogrammers.Schooly.views.admin;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import data.entity.Instructor;


public class ModifyFormInst extends FormLayout {
    Binder<Instructor> binder = new BeanValidationBinder<>(Instructor.class);
    TextField instID = new TextField("Instructor ID");
    TextField fName = new TextField("First name");
    TextField lName = new TextField("Last name");
    TextField email = new TextField("Email");

    TextField courseID = new TextField("Course ID");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    private Instructor instructor;

    public ModifyFormInst() {
        add(instID, fName, lName, email, courseID, editButtons());
        binder.bindInstanceFields(this);
    }

    public void setInstructor(Instructor instructor){
        this.instructor = instructor;
        binder.readBean(instructor);
    }
    private HorizontalLayout editButtons() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, instructor)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save, delete, cancel);

    }

    private void validateAndSave() {
        try {
            binder.writeBean(instructor);
            fireEvent(new SaveEvent(this, instructor));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

    // Events
    public static abstract class ModifyFormInstEvent extends ComponentEvent<ModifyFormInst> {
        private Instructor instructor;

        protected ModifyFormInstEvent(ModifyFormInst source, Instructor instructor) {
            super(source, false);
            this.instructor = instructor;
        }

        public Instructor getInstructor() {
            return instructor;
        }
    }

    public static class SaveEvent extends ModifyFormInstEvent {
        SaveEvent(ModifyFormInst source, Instructor instructor) {
            super(source, instructor);
        }
    }

    public static class DeleteEvent extends ModifyFormInstEvent {
        DeleteEvent(ModifyFormInst source, Instructor instructor) {
            super(source, instructor);
        }

    }

    public static class CloseEvent extends ModifyFormInstEvent {
        CloseEvent(ModifyFormInst source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}

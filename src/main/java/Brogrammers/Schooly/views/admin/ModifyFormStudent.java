package Brogrammers.Schooly.views.admin;

import Brogrammers.Schooly.Entity.Student;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import static Brogrammers.Schooly.APIAndOtherMethods.show;


public class ModifyFormStudent extends FormLayout {
    Notification notification;
    Binder<Student> binder = new BeanValidationBinder<>(Student.class);
    TextField fName = new TextField("First name");
    TextField lName = new TextField("Last name");
    EmailField email = new EmailField("Email");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    private Student student;


    public ModifyFormStudent() {
        add(fName, lName, email, editButtons());
        binder.bindInstanceFields(this);
    }

    /**
     * This method fills the text fields with the passed student attributes.
     * @param student
     */
    public void setStudent(Student student){
        this.student = student;
        binder.readBean(student);
    }

    /**
     * Designing the form layout and returning this layout.
     * This form also has clicklisteners and value change listeners.
     */
    private HorizontalLayout editButtons() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, student)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save, delete, cancel);

    }

    /**
     * This method checks if the text field is filled in properly.
     * If everything is filled in properly then it is saved.
     * If not this function notifies error.
     */
    private void validateAndSave() {
        try {
            binder.writeBean(student);
            if(student.getFName().isEmpty() || student.getLName().isEmpty()){
                notification = show("Provide valid name");
                return;
            } else if (student.getEmail().isEmpty()) {
                notification = show("Provide valid email");
                return;
            }
            fireEvent(new ModifyFormStudent.SaveEvent(this, student));
            notification = show("Student added");
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * These subclasses are used to register events like when save, delete and close.
     */
    public static abstract class ModifyFormStudEvent extends ComponentEvent<ModifyFormStudent> {
        private Student student;

        protected ModifyFormStudEvent(ModifyFormStudent source, Student student) {
            super(source, false);
            this.student = student;
        }

        public Student getStudent() {
            return student;
        }
    }

    public static class SaveEvent extends ModifyFormStudent.ModifyFormStudEvent {
        SaveEvent(ModifyFormStudent source, Student student) {
            super(source, student);
        }
    }

    public static class DeleteEvent extends ModifyFormStudent.ModifyFormStudEvent {
        DeleteEvent(ModifyFormStudent source, Student student) {
            super(source, student);
        }

    }

    public static class CloseEvent extends ModifyFormStudent.ModifyFormStudEvent {
        CloseEvent(ModifyFormStudent source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}


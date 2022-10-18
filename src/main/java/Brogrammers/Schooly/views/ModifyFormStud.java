package Brogrammers.Schooly.views;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import data.entity.Student;

public class ModifyFormStud extends FormLayout {
    Binder<Student> binder = new BeanValidationBinder<>(Student.class);
    TextField studID = new TextField("Student ID");
    TextField fName = new TextField("First name");
    TextField lName = new TextField("Last name");
    EmailField email = new EmailField("Email");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    private Student student;


    public ModifyFormStud() {
        add(studID, fName, lName, email, editButtons());
        binder.bindInstanceFields(this);
    }

    public void setStudent(Student student){
        this.student = student;
        binder.readBean(student);
    }

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
    private void validateAndSave() {
        try {
            binder.writeBean(student);
            fireEvent(new ModifyFormStud.SaveEvent(this, student));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }
    // Events
    public static abstract class ModifyFormStudEvent extends ComponentEvent<ModifyFormStud> {
        private Student student;

        protected ModifyFormStudEvent(ModifyFormStud source, Student student) {
            super(source, false);
            this.student = student;
        }

        public Student getStudent() {
            return student;
        }
    }

    public static class SaveEvent extends ModifyFormStud.ModifyFormStudEvent {
        SaveEvent(ModifyFormStud source, Student student) {
            super(source, student);
        }
    }

    public static class DeleteEvent extends ModifyFormStud.ModifyFormStudEvent {
        DeleteEvent(ModifyFormStud source, Student student) {
            super(source, student);
        }

    }

    public static class CloseEvent extends ModifyFormStud.ModifyFormStudEvent {
        CloseEvent(ModifyFormStud source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}

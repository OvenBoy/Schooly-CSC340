package Brogrammers.Schooly.views.admin;


import Brogrammers.Schooly.Entity.Take;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class ModifyFormStudentCourse extends FormLayout {
    Binder<Take> binder = new BeanValidationBinder<>(Take.class);
    TextField studID = new TextField("Student ID");
    TextField courseId = new TextField("Course ID");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    private Take take;

    public ModifyFormStudentCourse(){
        HorizontalLayout layout = new HorizontalLayout(studID, courseId,editButtons());
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        add(layout);
        binder.bindInstanceFields(this);
    }

    private HorizontalLayout editButtons() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> validateAndDelete());
        cancel.addClickListener(event -> fireEvent(new ModifyFormStudentCourse.CloseEvent(this)));

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(take);
            fireEvent(new ModifyFormStudentCourse.SaveEvent(this, take));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }
    private void validateAndDelete(){
        try {
            binder.writeBean(take);
            fireEvent(new ModifyFormStudentCourse.DeleteEvent(this, take));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }
    public void setTake(Take take) {
        this.take = take;
        binder.readBean(take);
    }

    public static abstract class ModifyFormStudentCourseEvent extends ComponentEvent<ModifyFormStudentCourse> {
        private Take take;

        protected ModifyFormStudentCourseEvent(ModifyFormStudentCourse source, Take take) {
            super(source, false);
            this.take = take;
        }

        public Take getTake() {
            return take;
        }
    }

    public static class SaveEvent extends ModifyFormStudentCourseEvent {
        SaveEvent(ModifyFormStudentCourse source, Take take) {
            super(source, take);
        }
    }

    public static class DeleteEvent extends ModifyFormStudentCourseEvent {
        DeleteEvent(ModifyFormStudentCourse source, Take take) {
            super(source, take);
        }

    }

    public static class CloseEvent extends ModifyFormStudentCourseEvent {
        CloseEvent(ModifyFormStudentCourse source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}

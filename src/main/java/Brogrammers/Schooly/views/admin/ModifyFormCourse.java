package Brogrammers.Schooly.views.admin;

import Brogrammers.Schooly.Entity.Course;
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

public class ModifyFormCourse extends FormLayout {
    Binder<Course> binder = new BeanValidationBinder<>(Course.class);
    TextField name = new TextField("Course Name");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    private Course course;

    public ModifyFormCourse() {
        add(name, editButtons());
        binder.bindInstanceFields(this);
    }

    private HorizontalLayout editButtons() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new ModifyFormCourse.DeleteEvent(this, course)));
        cancel.addClickListener(event -> fireEvent(new ModifyFormCourse.CloseEvent(this)));

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(course);
            fireEvent(new ModifyFormCourse.SaveEvent(this, course));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCourse(Course course) {
        this.course = course;
        binder.readBean(course);
    }

    public static abstract class ModifyFormCourseEvent extends ComponentEvent<ModifyFormCourse> {
        private Course course;

        protected ModifyFormCourseEvent(ModifyFormCourse source, Course course) {
            super(source, false);
            this.course = course;
        }

        public Course getCourse() {
            return course;
        }
    }

    public static class SaveEvent extends ModifyFormCourse.ModifyFormCourseEvent {
        SaveEvent(ModifyFormCourse source, Course course) {
            super(source, course);
        }
    }

    public static class DeleteEvent extends ModifyFormCourse.ModifyFormCourseEvent {
        DeleteEvent(ModifyFormCourse source, Course course) {
            super(source, course);
        }

    }

    public static class CloseEvent extends ModifyFormCourse.ModifyFormCourseEvent {
        CloseEvent(ModifyFormCourse source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}

package Brogrammers.Schooly.views.admin;

import Brogrammers.Schooly.Entity.Course;
import Brogrammers.Schooly.Entity.Instructor;
import Brogrammers.Schooly.Repository.CourseRepository;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.shared.Registration;

import java.util.List;


public class ModifyFormInstructor extends FormLayout {
    CourseRepository courseRepository;
    Binder<Instructor> binder = new BeanValidationBinder<>(Instructor.class);
    TextField fName = new TextField("First name");
    TextField lName = new TextField("Last name");
    TextField email = new TextField("Email");

    ComboBox<Integer> courseID = new ComboBox<>("CourseID");
    ComboBox<String> courseName = new ComboBox<>("Course Name");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    private Instructor instructor;

    public ModifyFormInstructor(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
        courseName.setAllowCustomValue(true);
        courseID.setAllowCustomValue(true);
        setComboBox();

        courseID.addValueChangeListener(e-> courseName.setValue(getCourseNameByID(courseID.getValue())));
        add(fName, lName, email, courseID, courseName, editButtons());
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
    public static abstract class ModifyFormInstEvent extends ComponentEvent<ModifyFormInstructor> {
        private Instructor instructor;

        protected ModifyFormInstEvent(ModifyFormInstructor source, Instructor instructor) {
            super(source, false);
            this.instructor = instructor;
        }

        public Instructor getInstructor() {
            return instructor;
        }
    }

    public static class SaveEvent extends ModifyFormInstEvent {
        SaveEvent(ModifyFormInstructor source, Instructor instructor) {
            super(source, instructor);
        }
    }

    public static class DeleteEvent extends ModifyFormInstEvent {
        DeleteEvent(ModifyFormInstructor source, Instructor instructor) {
            super(source, instructor);
        }

    }

    public static class CloseEvent extends ModifyFormInstEvent {
        CloseEvent(ModifyFormInstructor source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

    private void setComboBox(){
        courseID.setItems(courseRepository.searchInstructorFreeCourseID());
        courseName.setItems(courseRepository.searchInstructorFreeCourseName());
    }

    private String getCourseNameByID(Integer courseID){
        String s = courseRepository.searchByID(courseID);
        return s;
    }
    private Integer getCourseIDByName(String courseName){
        Integer i = courseRepository.searchByName(courseName);
        return i;
    }

}

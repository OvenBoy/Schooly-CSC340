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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.shared.Registration;

import java.util.List;

import static Brogrammers.Schooly.APIAndOtherMethods.show;


public class ModifyFormInstructor extends FormLayout {

    Notification notification;
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

        add(fName, lName, email, courseID, courseName, editButtons());
        binder.bindInstanceFields(this);
    }

    /**
     * This method fills the text fields with the passed instructor attributes.
     * @param instructor
     */
    public void setInstructor(Instructor instructor){
        this.instructor = instructor;
        binder.readBean(instructor);
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

        // These two value change listeners are used to populate one if the other is given/selected.
        courseID.addValueChangeListener(e-> courseName.setValue(getCourseNameByID(courseID.getValue())));
        courseName.addValueChangeListener(e-> courseID.setValue(getCourseIDByName(courseName.getValue())));
        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> deleteInst());
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
            binder.writeBean(instructor);
            if(instructor.getFName().isEmpty() || instructor.getLName().isEmpty()){
                notification = show("Provide valid name");
                return;
            } else if (instructor.getEmail().isEmpty()) {
                notification = show("Provide valid email");
                return;
            } else if (instructor.getCourseName() == null) {
                notification = show("Instructor without course created");
            }
            fireEvent(new SaveEvent(this, instructor));
            setComboBox();
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * This method deletes a instructor and updates the list.
     */
    private void deleteInst(){
        fireEvent(new DeleteEvent(this, instructor));
        setComboBox();
    }

    /**
     * These subclasses are used to register events like when save, delete and close.
     */
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

    /**
     * Setting up the combo box values.
     */
    private void setComboBox(){
        courseID.setItems(courseRepository.searchInstructorFreeCourseID());
        courseName.setItems(courseRepository.searchInstructorFreeCourseName());
    }

    /**
     * Finding the course name of a given course id.
     * @param courseID
     * @return
     */
    private String getCourseNameByID(Integer courseID){
        String s = courseRepository.searchByID(courseID);
        return s;
    }

    /**
     * Finding the course ID of a given course name.
     * @param courseName
     * @return
     */
    private Integer getCourseIDByName(String courseName){
        Integer i = courseRepository.searchByName(courseName);
        return i;
    }

}

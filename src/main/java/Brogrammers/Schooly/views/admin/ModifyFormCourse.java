package Brogrammers.Schooly.views.admin;

import Brogrammers.Schooly.Entity.Course;
import Brogrammers.Schooly.Entity.Instructor;
import Brogrammers.Schooly.Repository.CourseRepository;
import Brogrammers.Schooly.Repository.InstructorRepository;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class ModifyFormCourse extends FormLayout {
    CourseRepository courseRepository;
    InstructorRepository instructorRepository;
    Notification notification;
    Binder<Course> binder = new BeanValidationBinder<>(Course.class);
    TextField name = new TextField("Course Name");
    List<Course> courseList;
    List<Instructor>instructorList;

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    private Course course;

    public ModifyFormCourse(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        updateLists();

        HorizontalLayout layout = new HorizontalLayout(name, editButtons());
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        add(layout);
        binder.bindInstanceFields(this);
    }

    private void updateLists(){
        courseList = this.courseRepository.findAll();
        instructorList = this.instructorRepository.findAll();
    }
    private HorizontalLayout editButtons() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> validateAndDelete());
        cancel.addClickListener(event -> fireEvent(new ModifyFormCourse.CloseEvent(this)));

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(course);
            if(course.getName().isEmpty()){
                notification= show("Cannot insert an empty course");
                return;
            }
            else if (checkingCourse(course.getName()) == 1){
                notification = show("Duplicate Course Name");
                return;
            }
            fireEvent(new ModifyFormCourse.SaveEvent(this, course));
            updateLists();

        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateAndDelete(){
        Notification notification;

        try {
            binder.writeBean(course);
            Integer num = checkingCourse(course.getName());
            if (course.getName().isEmpty()) {
                notification = show("Cannot delete an empty course, put a course name");
                return;
            } else if (num.equals(0)) {
                notification = show("Course doesn't exist, put a valid course name");
                return;
            } else if (checkingDeleteValidationCourse(course.getCourseID()).equals(1)) {
                notification = show("Course is assigned to an instructor. Delete the instructor first.");
                return;
            } else if (!num.equals(0)){
                course.setCourseID(num);
            }
            else{};
            notification = show("Deleted " + course.getName());
            fireEvent(new ModifyFormCourse.DeleteEvent(this, course));
            setVisible(false);
            updateLists();
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

    public Notification show(String error){
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        Div text = new Div(new Text(error));

        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });

        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        notification.add(layout);
        notification.open();

        notification.setPosition(Notification.Position.MIDDLE);

        return notification;
    }

    private Integer checkingCourse(String courseName){
        for (Course course: courseList) {
            if(course.getName().equals(courseName)){
                return course.getCourseID();
            }
        }
        return 0;
    }

    private Integer checkingDeleteValidationCourse(Integer courseID){
        for(Instructor instructor : instructorList){
            if(instructor.getCourseID().equals(courseID)){
                return 1;
            }
        }
        return 0;
    }


}

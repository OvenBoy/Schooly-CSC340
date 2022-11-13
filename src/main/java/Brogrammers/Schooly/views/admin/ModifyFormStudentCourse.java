package Brogrammers.Schooly.views.admin;


import Brogrammers.Schooly.Entity.Take;
import Brogrammers.Schooly.Repository.TakeRepository;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

import static Brogrammers.Schooly.APIAndOtherMethods.show;

public class ModifyFormStudentCourse extends FormLayout {
    Notification notification;
    TakeRepository takeRepository;
    Binder<Take> binder = new BeanValidationBinder<>(Take.class);

    ComboBox<Integer> studID = new ComboBox<>("Student ID");
    ComboBox<Integer> courseId = new ComboBox<>("Course ID");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    private Take take;

    public ModifyFormStudentCourse(TakeRepository takeRepository){
        this.takeRepository = takeRepository;
        studID.setAllowCustomValue(true);
        courseId.setAllowCustomValue(true);
        setComboBox();
        HorizontalLayout layout = new HorizontalLayout(studID, courseId,editButtons());
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        add(layout);
        binder.bindInstanceFields(this);
    }

    private void setComboBox() {
        this.studID.setItems(this.takeRepository.searchStudID());
        this.courseId.setItems(this.takeRepository.searchCourseID());
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

            if(take.getStudID() == null){
                notification = show("Provide a Student ID");
                return;
            }
            else if(take.getCourseID() == null){
                notification = show("Provide a Course ID");
                return;
            } else if (checkingTakeForSaveORDelete(take) == 1) {
                notification = show("This student is already in this class");
                return;
            }
            fireEvent(new ModifyFormStudentCourse.SaveEvent(this, take));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }
    private void validateAndDelete(){
        try {

            binder.writeBean(take);
            // check if take exists
            if (checkingTakeForSaveORDelete(take) == 0){
                notification = show("This student is not registered in this class");
                return;
            }
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

    private Integer checkingTakeForSaveORDelete(Take take){
        for (Take t:this.takeRepository.findAll()) {
            if(t.getStudID().equals(take.getStudID()) && (t.getCourseID().equals(take.getCourseID()))){
                return 1;
            }
        }
        return 0;
    }

}

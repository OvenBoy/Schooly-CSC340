package Brogrammers.Schooly.views;

import Brogrammers.Schooly.Entity.StudAssign;
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


public class GradeForm extends FormLayout {

    private StudAssign studAssign;

    Binder<StudAssign> bind = new BeanValidationBinder<>(StudAssign.class);

    //Text Fields
    TextField courseID = new TextField("Course ID");
    TextField name = new TextField("Assignment Name");
    TextField grade = new TextField("Grade");
    TextField studID = new TextField("Student ID");

    //Button Declarations
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public GradeForm() {
        addClassName("grade-form");
        add(courseID,name,grade,studID,createButtonsLayout());
        bind.bindInstanceFields(this);
    }

    public void setStudAssign(StudAssign studAssign) {
        this.studAssign = studAssign;
        bind.readBean(studAssign);
    }

    private void gradeSave() {
        try {
            bind.writeBean(studAssign);
            fireEvent(new SavEvent(this, studAssign));
        } catch (ValidationException err) {
            throw new RuntimeException(err);
        }
    }

    public static class CloseEvent extends GradeFormEvent {
        CloseEvent(GradeForm source) {
            super(source, null);
        }
    }

    public static class DelEvent extends GradeFormEvent {
        DelEvent(GradeForm source, StudAssign studAssign) {
            super(source, studAssign);
        }
    }

    public static class SavEvent extends GradeFormEvent {
        SavEvent(GradeForm source, StudAssign studAssign) {
            super(source, studAssign);
        }
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> gradeSave());
        delete.addClickListener(event -> fireEvent(new DelEvent(this, studAssign)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    public static abstract class GradeFormEvent extends ComponentEvent<GradeForm> {
        private StudAssign studAssign;
        protected GradeFormEvent(GradeForm source, StudAssign studAssign) {
            super(source, false);
            this.studAssign = studAssign;
        }
        public StudAssign getStudAssign() {
            return studAssign;
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
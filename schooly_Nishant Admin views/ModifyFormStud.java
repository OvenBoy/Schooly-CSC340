package Brogrammers.Schooly.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
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
        binder.bindInstanceFields(this);
        add(studID, fName, lName, email, editButtons());

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

        return new HorizontalLayout(save, delete, cancel);

    }
}

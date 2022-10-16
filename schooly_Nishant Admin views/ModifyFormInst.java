package Brogrammers.Schooly.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import data.entity.Instructor;
import data.repository.InstructorRepository;

public class ModifyFormInst extends FormLayout {
    private InstructorRepository instructorRepository;
    Binder<Instructor> binder = new BeanValidationBinder<>(Instructor.class);
    TextField instID = new TextField("Instructor ID");
    TextField fName = new TextField("First name");
    TextField lName = new TextField("Last name");
    TextField email = new TextField("Email");

    TextField courseID = new TextField("Course ID");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    public ModifyFormInst() {
        add(instID, fName, lName, email, courseID, editButtons());
        binder.bindInstanceFields(this);
        save.addClickListener(buttonClickEvent -> {
            try{
                Instructor instructor = new Instructor();
                binder.writeBean(instructor);
                instructorRepository.save(instructor);
                binder.readBean(new Instructor());

            }catch (ValidationException e){}
        });

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

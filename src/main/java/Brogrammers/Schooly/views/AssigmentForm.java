package Brogrammers.Schooly.views;

import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import data.Assignment;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.List;

public class AssigmentForm extends FormLayout {
    private Assignment assignment;
    Binder<Assignment> binder = new BeanValidationBinder<>(Assignment.class);
    TextField assignmentName = new TextField("Assignment Name");
    TextField assignmentDetails = new TextField("Assignment Details");
    TextField dueDate = new TextField("Due Date");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public AssigmentForm(List<Assignment> assignments) {
        addClassName("Assignment-form");
        add(assignmentName,assignmentDetails,dueDate,createButtonsLayout());
        binder.bindInstanceFields(this);
   }

    public void setAssignmentName(TextField assignmentName) {
        this.assignmentName = assignmentName;
        binder.readBean(assignment);
    }

    public void setAssignmentDetails(TextField assignmentDetails) {
        this.assignmentDetails = assignmentDetails;
        binder.readBean(assignment);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }

}



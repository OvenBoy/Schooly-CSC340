package Brogrammers.Schooly.views.instructor;

import Brogrammers.Schooly.Entity.Assignment;
import Brogrammers.Schooly.Repository.AssignmentRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacement;
import Brogrammers.Schooly.views.AssigmentForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Boolean.TRUE;

@RolesAllowed("ROLE_TEACHER")
@Route(value = "/Assignments", layout = AppLayoutNavbarPlacement.class)
public class AssignmentView extends VerticalLayout{
    AssignmentRepository assignmentRepository;
    Grid<Assignment> grid = new Grid<>(Assignment.class);
    AssigmentForm form;

    public AssignmentView(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;

        addClassName("Assignments");
        setSizeFull();
        configureGrid();
        configureForm();
        add(new H1("Assignments"), buttonConfig());

        updateList();
        closeForm();
        add(getContent());
    }

    public Component buttonConfig() {
        Button  newAssignmentButton = new Button("New Assignment");
        newAssignmentButton.addClickListener(e -> addAssignment());

        HorizontalLayout button = new HorizontalLayout(newAssignmentButton);

        return button;
    }

    private void addAssignment() {
        grid.asSingleSelect().clear();
        editAssign(new Assignment());
    }

    private void closeForm() {
        form.setAssignment(null);
        form.setVisible(false);
    }

    private void configureGrid() {
          grid.addClassNames("contact-grid");
          grid.setSizeFull();
          grid.setItems(assignmentRepository.findAll());
          grid.getColumns().forEach(col -> col.setAutoWidth(TRUE));

          grid.asSingleSelect().addValueChangeListener(e -> editAssign(e.getValue()));
    }

    private Component getContent() {
        HorizontalLayout assignment = new HorizontalLayout(grid, form);
        assignment.setFlexGrow(2, grid);
        assignment.setFlexGrow(1, form);
        assignment.addClassNames("content");
        assignment.setSizeFull();
        return assignment;
    }

    private void delAssign(AssigmentForm.DeleteEvent event) {
        assignmentRepository.delete(event.getAssignment());
        updateList();
        closeForm();
    }

    private void savAssign(AssigmentForm.SaveEvent event) {
        assignmentRepository.save(event.getAssignment());
        updateList();
        closeForm();
    }

    private void editAssign(Assignment assignment) {
        if (assignment == null) {
            closeForm();
        } else {
            form.setAssignment(assignment);
            form.setVisible(true);
        }
    }

    private void configureForm() {
        form = new AssigmentForm();
        form.setWidth("25em");

        form.addListener(AssigmentForm.SaveEvent.class, this::savAssign);
        form.addListener(AssigmentForm.DeleteEvent.class, this::delAssign);
        form.addListener(AssigmentForm.CloseEvent.class, err -> closeForm() );
    }

    private void updateList() {
        grid.setItems(assignmentRepository.findAll());
    }
}

package Brogrammers.Schooly.views.instructor;

import Brogrammers.Schooly.Entity.Assignment;
import Brogrammers.Schooly.Repository.AssignmentRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacement;
import Brogrammers.Schooly.views.AssigmentForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RolesAllowed("ROLE_TEACHER")
@Route(value = "/Assignments", layout = AppLayoutNavbarPlacement.class)
public class AssignmentView extends VerticalLayout{
    AssignmentRepository assignmentRepository;
    Grid<Assignment> grid = new Grid<>(Assignment.class);
    AssigmentForm form;
    TextField filterText = new TextField();

    public AssignmentView(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
        addClassName("Assignments");
        setSizeFull();
        configureGrid();
        configureForm();


        add(getContent());
        updateList();
    }

    private void configureGrid() {
          grid.addClassNames("contact-grid");
          grid.setSizeFull();
          grid.setItems(assignmentRepository.findAll());
    }

    private Component getContent() {
        HorizontalLayout assignment = new HorizontalLayout(grid, form);
        assignment.setFlexGrow(2, grid);
        assignment.setFlexGrow(1, form);
        assignment.addClassNames("content");
        assignment.setSizeFull();
        return assignment;
    }


    private void configureForm() {
        form = new AssigmentForm(Collections.emptyList());
        form.setWidth("25em");
    }

    private void updateList() {


    }
}

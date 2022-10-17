package com.schooly.demo.teacher;

import com.schooly.demo.teacher.AppLayoutNavbarPlacement;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import data.Assignment;

import javax.annotation.security.PermitAll;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@PermitAll
@Route(value = "/Assignments", layout = AppLayoutNavbarPlacement.class)
public class AssignmentView extends VerticalLayout{

    Grid<Assignment> grid = new Grid<>(Assignment.class);
    AssigmentForm form;
    TextField filterText = new TextField();
    List<Assignment> assignments = Arrays.asList(
            new Assignment("Homework One", "Work on problems 1-5 on Page 57 of your book", "Oct 10"),
            new Assignment("Quiz One", "Study for Quiz One", "Oct 18"));

    public AssignmentView() {
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
          grid.setItems(assignments);
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

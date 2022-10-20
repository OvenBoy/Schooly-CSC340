package Brogrammers.Schooly.views.instructor;

import Brogrammers.Schooly.views.AppLayoutNavbarPlacement;
import Brogrammers.Schooly.views.GradeForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import data.entity.Grades;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@PermitAll
@RolesAllowed("ROLE_TEACHER")
@Route(value = "/StudentGrades", layout = AppLayoutNavbarPlacement.class)
public class GradeView extends VerticalLayout{

    Grid<Grades> grid = new Grid<>(Grades.class);
    GradeForm form;

    List<Grades> grades = Arrays.asList(
            new Grades("HomeWork-One", "Darwin Nunezm", "7/10"),
            new Grades("HomeWork-One", "Michael Thomas", "8/10"),
            new Grades("HomeWork-One", "Einstein Brooks", "10/10"),
            new Grades("Quiz-One", "Darwin Nunezm", "12/15"),
            new Grades("Quiz-One", "Michael Thomas", "15/15"),
            new Grades("Quiz-One", "Einstein Brooks", "4/15"));


    /*
    Main class for the Grade dashboard
     */
    public GradeView() {
        addClassName("Grade-View");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getContent());
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setItems(grades);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Component getContent() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(grid, form);
        horizontalLayout.setFlexGrow(2, grid);
        horizontalLayout.setFlexGrow(1, form);
        horizontalLayout.addClassNames("content");
        horizontalLayout.setSizeFull();
        return horizontalLayout;
    }

    private void configureForm() {
        form = new GradeForm(Collections.emptyList());
        form.setWidth("25em");
    }
}

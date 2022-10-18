package Brogrammers.Schooly.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import data.Stu_Assignments;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;


@Route(value ="assignments", layout = AppLayoutNavbarPlacementStudent.class)
@PageTitle("Assignments | Schooly")
@PermitAll
public class StudentAssignmentView extends VerticalLayout {
    Grid<Stu_Assignments> grid = new Grid<>(Stu_Assignments.class);
    H2 currentPage = new H2("Assignments");

    public StudentAssignmentView() {
        addClassName("list-view");

        setSizeFull();
        configureGrid();


        List<Stu_Assignments> assignments = new ArrayList<Stu_Assignments>();
        assignments.add(new Stu_Assignments("CSC-340", "Project Prototype",
                "10/20/2022", "11AM", "100"));
        assignments.add(new Stu_Assignments("CSC-340", "Test-Case Assignment",
                "10/18/2022", "12AM", "100"));
        assignments.add(new Stu_Assignments("HIS-101", "History Paper 3",
                "10/18/2022", "1PM", "100"));

        grid.setItems(assignments);

        add(currentPage, new Hr() ,grid);
    }
    private void configureGrid() {
        grid.addClassName("grade-grid");
        grid.setSizeFull();
        grid.setColumns("courseTitle", "assignmentTitle", "dueDate", "time");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        //grid.setSortableColumns("dueDate");
        grid.setMultiSort(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);

    }
}

package Brogrammers.Schooly.views.student;

import Brogrammers.Schooly.views.AppLayoutNavbarPlacementStudent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import data.entity.Stu_Grades;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@Route(value = "grades", layout = AppLayoutNavbarPlacementStudent.class)
@PageTitle("Grades | Schooly")
@RolesAllowed("ROLE_STUDENT")
public class StudentGradeView extends VerticalLayout {

    Grid<Stu_Grades> grid = new Grid<>(Stu_Grades.class);
    H2 currentPage = new H2("Grades");

    public StudentGradeView() {
        addClassName("student-grade-view");
        setSizeFull();
        configureGrid();


        List<Stu_Grades> grades = new ArrayList<Stu_Grades>();
        grades.add(new Stu_Grades("CSC-340", "Test-Case Assignment",
                "90/100", ((90.0 / 100.0) * 100.0)));
        grades.add(new Stu_Grades("MAT-222", "Exam 2",
                "92/100", ((92.0 / 100.0) * 100.0)));
        grades.add(new Stu_Grades("HIS-101","History Assignment",
                "150/250", ((150.0 / 250.0) * 100.0)));




        grid.setItems(grades);

        add(currentPage, new Hr(), grid);

    }
    private void configHeader() {
        HorizontalLayout header = new HorizontalLayout(
                currentPage
        );
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER
        );
        header.setAlignSelf(FlexComponent.Alignment.AUTO);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-s");
    }

    private void configureGrid() {
        grid.addClassName("grade-grid");
        grid.setSizeFull();
        grid.setColumns("courseTitle", "assignmentTitle", "fractionGrade", "percentGrade");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setSortableColumns();
        grid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
    }

}

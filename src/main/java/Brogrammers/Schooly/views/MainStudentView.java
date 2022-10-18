package Brogrammers.Schooly.views;

import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.grid.Grid;

import data.Stu_AssignmentDetailsFormLayout;
import data.Stu_Assignments;
import data.Stu_Grades;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;


@Route(value = "dashboard", layout= AppLayoutNavbarPlacementStudent.class)
@PageTitle("Dashboard | Schooly")
@PermitAll
public class MainStudentView extends VerticalLayout{
    Grid<Stu_Assignments> assGrid = new Grid<>(Stu_Assignments.class);
    Grid<Stu_Grades> gradeGrid = new Grid<>(Stu_Grades.class);


    H2 currentPage = new H2("Dashboard");
    H3 assTitle = new H3("Assignments");
    H3 gradeTitle = new H3("Grades");



    public MainStudentView() {
        addClassName("list-view");
        setSizeFull();
        configHeader();
        HorizontalLayout hlayout = new HorizontalLayout(assGrid, gradeGrid);
        HorizontalLayout textLayout = new HorizontalLayout(assTitle, gradeTitle);

        VerticalLayout page = new VerticalLayout(textLayout, hlayout);

        assTitle.setWidth("100%");
        gradeTitle.setWidth("100%");
        textLayout.setVerticalComponentAlignment(Alignment.CENTER);

        page.setWidth("100%");
        page.setPadding(false);
        page.setSpacing(false);
        page.getThemeList().add("spacing-xs");

        configureGrid();


        List<Stu_Grades> grades = new ArrayList<Stu_Grades>();
        grades.add(new Stu_Grades("CSC-340", "Test-Case Assignment",
                "90/100", ((90.0 / 100.0) * 100.0)));
        grades.add(new Stu_Grades("MAT-222", "Exam 2",
                "92/100", ((92.0 / 100.0) * 100.0)));
        grades.add(new Stu_Grades("HIS-101","History Assignment",
                "150/250", ((150.0 / 250.0) * 100.0)));

        List<Stu_Assignments> assignments = new ArrayList<Stu_Assignments>();
        assignments.add(new Stu_Assignments("CSC-340", "Project Prototype",
                "10/20/2022", "11AM", "100"));
        assignments.add(new Stu_Assignments("CSC-340", "Test-Case Assignment",
                "10/18/2022", "12AM", "100"));
        assignments.add(new Stu_Assignments("HIS-101", "History Paper 3",
                "10/18/2022", "1PM", "100"));

        gradeGrid.setItems(grades);
        assGrid.setItems(assignments);

        assGrid.setItemDetailsRenderer(createAssignmentDetailRenderer());

        textLayout.setPadding(true);
        textLayout.setWidth("100%");

        hlayout.setPadding(true);
        hlayout.setWidth("100%");

        Hr border = new Hr();

        add(currentPage, border, page);
    }

    private ComponentRenderer<Stu_AssignmentDetailsFormLayout, Stu_Assignments> createAssignmentDetailRenderer() {
        return new ComponentRenderer<>(Stu_AssignmentDetailsFormLayout::new, Stu_AssignmentDetailsFormLayout::setAssignment);
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
        assGrid.addClassNames("assignment-grid");
        assGrid.setWidth("Auto");
        assGrid.setColumns("courseTitle", "assignmentTitle", "dueDate", "time");
        assGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        assGrid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        gradeGrid.addClassNames("grade-grid");
        gradeGrid.setWidth("Auto");
        gradeGrid.setColumns("courseTitle", "assignmentTitle", "fractionGrade", "percentGrade");
        gradeGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        gradeGrid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES,
            GridVariant.MATERIAL_COLUMN_DIVIDERS);
    }
}

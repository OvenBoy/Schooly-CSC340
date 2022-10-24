package Brogrammers.Schooly.views.student;

import Brogrammers.Schooly.views.AppLayoutNavbarPlacementStudent;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.*;
import com.vaadin.flow.component.grid.Grid;

import data.entity.Stu_AssignmentDetailsFormLayout;
import data.entity.Stu_Assignments;
import data.entity.Stu_Grades;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;


@Route(value = "student", layout= AppLayoutNavbarPlacementStudent.class)
@PageTitle("Dashboard | Schooly")
@RolesAllowed("ROLE_STUDENT")

public class MainStudentView extends VerticalLayout {
    protected Grid<Stu_Assignments> assGrid = new Grid<>(Stu_Assignments.class);
    protected Grid<Stu_Grades> gradeGrid = new Grid<>(Stu_Grades.class);

    protected H2 currentPage = new H2("Dashboard");
    protected H3 assTitle = new H3("Assignments");
    protected H3 gradeTitle = new H3("Grades");



    public MainStudentView() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        H3 welcome = new H3("Welcome " + username + "!");
        welcome.getStyle().set("margin-top", "0px");


        addClassName("student-main-view");
        setSpacing(false);
        getThemeList().add("spacing-s");
        setSizeFull();
        configHeader();
        HorizontalLayout hlayout = new HorizontalLayout(assGrid, gradeGrid); // Grids for Assingments and Grades
        HorizontalLayout textLayout = new HorizontalLayout(assTitle, gradeTitle); // Titles above respective grid

        //add above to vertical layout
        VerticalLayout page = new VerticalLayout(textLayout, hlayout);

        assTitle.setWidth("100%");
        gradeTitle.setWidth("100%");
        textLayout.setVerticalComponentAlignment(Alignment.CENTER);

        //Configure Page
        page.setWidth("100%");
        page.setPadding(false);
        page.setSpacing(false);
        page.getThemeList().add("spacing-xs");

        configureGrid();

        //hard-coded data
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

        //add items to grids
        gradeGrid.setItems(grades);
        assGrid.setItems(assignments);
        //drop-down for assignment grid
        assGrid.setItemDetailsRenderer(createAssignmentDetailRenderer());

        textLayout.setPadding(true);
        textLayout.setWidth("100%");

        hlayout.setPadding(true);
        hlayout.setWidth("100%");

        //Hr border = new Hr();
        welcome.getStyle().set("color", "hsl(214, 100%, 43%)");

        add(currentPage, welcome, new Hr(), page);
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

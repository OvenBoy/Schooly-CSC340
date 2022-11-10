package Brogrammers.Schooly.views.student;

import Brogrammers.Schooly.Entity.Assignment;
import Brogrammers.Schooly.Entity.Course;
import Brogrammers.Schooly.Repository.AssignmentRepository;
import Brogrammers.Schooly.Repository.CourseRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacementStudent;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import data.entity.Stu_AssignmentDetailsFormLayout;
import data.entity.Stu_Assignments;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Route(value ="assignments", layout = AppLayoutNavbarPlacementStudent.class)
@PageTitle("Assignments | Schooly")
@RolesAllowed("ROLE_STUDENT")
public class StudentAssignmentView extends VerticalLayout {
    protected Grid<Assignment> grid = new Grid<>();
    protected H2 currentPage = new H2("Assignments");
    AssignmentRepository assignmentRepository;
    CourseRepository courseRepo;

    public StudentAssignmentView(AssignmentRepository assignmentRepository, CourseRepository courseRepo) {
        this.assignmentRepository = assignmentRepository;
        this.courseRepo = courseRepo;
        addClassName("stu-assignment-view");
        setSizeFull();
        configureGrid();

        grid.setItemDetailsRenderer(createAssignmentDetailRenderer());

        add(currentPage, new Hr() ,grid);
        updateGrid();
    }



    private ComponentRenderer<AssignmentPageFormLayout, Assignment> createAssignmentDetailRenderer() {
        return new ComponentRenderer<>(AssignmentPageFormLayout::new, AssignmentPageFormLayout::setAssignment);
    }

    private void configureGrid() {
        grid.addClassName("assignment-grid");
        grid.setSizeFull();
        grid.addColumn(Assignment::getName).setHeader("Assignment");
        grid.addColumn(Assignment::getDescription).setHeader("Description");
        grid.addColumn(Assignment::getDueDate).setHeader("Due Date");
        //grid.addColumn(Course::getName).setHeader("Course");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        //grid.setSortableColumns("dueDate");
        grid.setMultiSort(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);

    }
    private void updateGrid() {
        grid.setItems(assignmentRepository.findAll());
        grid.setItems(assignmentRepository.nameSearch());
    }
    private static class AssignmentPageFormLayout extends FormLayout{
        private final TextField courseTitle = new TextField("Course Title");
        private final TextField assignmentTitle = new TextField("Assignment");
        private final TextField dueDate = new TextField("Due Date");
//        private final TextField time = new TextField("Due Time");
//        private final TextField possPoints = new TextField("Possible Points");
        private final TextField description = new TextField("Description");
        private final ComboBox<String> status = new ComboBox<>("Status");

        public AssignmentPageFormLayout(){
            Stream.of(courseTitle, assignmentTitle, dueDate, description)
                    .forEach(field -> {
                        field.setReadOnly(true);
//                        status.setReadOnly(false);
//                        status.setItems("Done", "In-progress");
                        add(field);
                    });
            setResponsiveSteps(new ResponsiveStep("0", 3));
            setColspan(courseTitle, 1);
            setColspan(assignmentTitle, 1);
            //setColspan(status, 1);
            setColspan(dueDate, 1);
            setColspan(description, 3);
            //status.


        }
        public void setAssignment(Assignment assignment){
            courseTitle.setValue(assignment.getCourseID().toString());
            assignmentTitle.setValue(assignment.getName());
            dueDate.setValue(assignment.getDueDate().toString());
            //time.setValue(assignment.getTime());
            //possPoints.setValue(assignment.getPossiblePoints());
            description.setValue(assignment.getDescription());

        }
    }
}


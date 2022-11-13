package Brogrammers.Schooly.views.student;

import Brogrammers.Schooly.Entity.Assignment;
import Brogrammers.Schooly.Entity.AssignmentStudentview;
import Brogrammers.Schooly.Entity.Course;
import Brogrammers.Schooly.Repository.AssignmentRepository;
import Brogrammers.Schooly.Repository.AssignmentStudentviewRepository;
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
import org.springframework.expression.spel.ast.Assign;

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
    protected Grid<AssignmentStudentview> grid = new Grid<>(AssignmentStudentview.class, false);
    protected H2 currentPage = new H2("Assignments");
    AssignmentStudentviewRepository assignmentRepository;
    //CourseRepository courseRepo;

    public StudentAssignmentView(AssignmentStudentviewRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
        //this.courseRepo = courseRepo;
        addClassName("stu-assignment-view");
        setSizeFull();
        configureGrid();

        grid.setItemDetailsRenderer(createAssignmentDetailRenderer());

        add(currentPage, new Hr() ,grid);
        updateGrid();
    }



    private ComponentRenderer<AssignmentPageFormLayout, AssignmentStudentview> createAssignmentDetailRenderer() {
        return new ComponentRenderer<>(AssignmentPageFormLayout::new, AssignmentPageFormLayout::setAssignment);
    }

    private void configureGrid() {
        grid.addClassName("assignment-grid");
        grid.setSizeFull();
        grid.addColumn(AssignmentStudentview::getCourseName).setHeader("Course");
        grid.addColumn(AssignmentStudentview::getAssignmentName).setHeader("Assignment");
        grid.addColumn(AssignmentStudentview::getDescription).setHeader("Description");
        grid.addColumn(AssignmentStudentview::getDueDate).setHeader("Due Date").setSortable(true);


        grid.getColumns().forEach(col -> col.setAutoWidth(true));
//        grid.setSortableColumns("Assignment");
//        grid.setMultiSort(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);

    }
    private void updateGrid() {
        grid.setItems(assignmentRepository.findAll());
        //grid.setItems(assignmentRepository.nameSearch());
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
        public void setAssignment(AssignmentStudentview assignment){
            courseTitle.setValue(assignment.getCourseName());
            assignmentTitle.setValue(assignment.getAssignmentName());
            dueDate.setValue(assignment.getDueDate().toString());
            //time.setValue(assignment.getTime());
            //possPoints.setValue(assignment.getPossiblePoints());
            description.setValue(assignment.getDescription());

        }
    }
}


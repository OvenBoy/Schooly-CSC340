package Brogrammers.Schooly.views.student;

import Brogrammers.Schooly.Entity.Assignment;
import Brogrammers.Schooly.Entity.AssignmentId;
import Brogrammers.Schooly.Repository.AssignmentRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Route(value ="assignments", layout = AppLayoutNavbarPlacementStudent.class)
@PageTitle("Assignments | Schooly")
@RolesAllowed("ROLE_STUDENT")
public class StudentAssignmentView extends VerticalLayout {
    protected Grid<Assignment> grid = new Grid<>(Assignment.class);
    protected H2 currentPage = new H2("Assignments");
    AssignmentRepository assignmentRepository;

    public StudentAssignmentView(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
        addClassName("stu-assignment-view");
        setSizeFull();
        configureGrid();
        updateGrid();

        //grid.setItemDetailsRenderer(createAssignmentDetailRenderer());

//        grid.setItems(assignments);

        add(currentPage, new Hr() ,grid);
    }

    private void updateGrid() {

        grid.setItems(assignmentRepository.search());
    }

    private ComponentRenderer<AssignmentPageFormLayout, Stu_Assignments> createAssignmentDetailRenderer() {
        return new ComponentRenderer<>(AssignmentPageFormLayout::new, AssignmentPageFormLayout::setAssignment);
    }

    private void configureGrid() {
        //grid.addClassName("grade-grid");
        grid.setSizeFull();
        //grid.setColumns("courseID", "dueDate");
        grid.addColumn(assignment -> assignment.getCourseID()).setHeader("courseID");
        grid.addColumn(Assignment::getDueDate).setHeader("dueDate");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        //grid.setSortableColumns("dueDate");
        //grid.setMultiSort(true);
//        grid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
//                GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);

    }
    private static class AssignmentPageFormLayout extends FormLayout{
        private final TextField courseTitle = new TextField("Course Title");
        private final TextField assignmentTitle = new TextField("Assignment");
        private final TextField dueDate = new TextField("Due Date");
        private final TextField time = new TextField("Due Time");
        private final TextField possPoints = new TextField("Possible Points");
        private final TextField description = new TextField("Description");
        private final ComboBox<String> status = new ComboBox<>("Status");

        public AssignmentPageFormLayout(){
            Stream.of(courseTitle, assignmentTitle, status, dueDate, time, possPoints, description)
                    .forEach(field -> {
                        field.setReadOnly(true);
                        status.setReadOnly(false);
                        status.setItems("Done", "In-progress");
                        add(field);
                    });
            setResponsiveSteps(new ResponsiveStep("0", 3));
            setColspan(courseTitle, 1);
            setColspan(assignmentTitle, 1);
            setColspan(status, 1);
            setColspan(dueDate, 1);
            setColspan(description, 3);
            //status.


        }
        public void setAssignment(Stu_Assignments assignment){
            courseTitle.setValue(assignment.getCourseTitle());
            assignmentTitle.setValue(assignment.getAssignmentTitle());
            dueDate.setValue(assignment.getDueDate());
            time.setValue(assignment.getTime());
            possPoints.setValue(assignment.getPossiblePoints());
            description.setValue(assignment.getDescription());

        }
    }
}


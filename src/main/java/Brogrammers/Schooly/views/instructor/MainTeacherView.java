package Brogrammers.Schooly.views.instructor;

import Brogrammers.Schooly.Entity.Student;
import Brogrammers.Schooly.Repository.StudentRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacement;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import javax.annotation.security.RolesAllowed;

import static java.lang.Boolean.TRUE;

/**
 * This Class is used to create the main teacher view where an instructor
 * can view the students in their class.
 *
 * Last Edited: 11/13/2022
 * Edited By: Andrew Van Es
 */
@RolesAllowed("ROLE_TEACHER")
@Route(value = "teacher", layout = AppLayoutNavbarPlacement.class)
public class MainTeacherView extends VerticalLayout{

    // Declaring Variables
    StudentRepository studentRepository;

    Grid<Student> grid = new Grid<>();

    /**
     * This is the main class for the MainTeacherView
     * @param studentRepository used to access the student tables in the database.
     */
    public MainTeacherView(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

        addClassName("Teacher-Main-View");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
        updateList();
    }

    /**
     * This method is used to configure the gird and its entries.
     */
    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();

        grid.addColumn(Student::getId).setHeader("Student ID");
        grid.addColumn(Student::getFName).setHeader("First Name");
        grid.addColumn(Student::getLName).setHeader("Last Name");
        grid.addColumn(Student::getEmail).setHeader(("Email"));


        grid.getColumns().forEach(col -> col.setAutoWidth(TRUE));

    }

    /**
     * This method is used to construct the toolbar
     */
    private HorizontalLayout getToolbar() {
        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    /**
     * This method is used to update the grid with new entries
     */
    private void updateList() {
        grid.setItems(studentRepository.findAll());
    }
}
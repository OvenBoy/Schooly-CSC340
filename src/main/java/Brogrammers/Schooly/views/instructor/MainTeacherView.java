package Brogrammers.Schooly.views.instructor;

import Brogrammers.Schooly.Entity.Student;
import Brogrammers.Schooly.Repository.StudentRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacement;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;
import data.entity.User1;

import static java.lang.Boolean.TRUE;

@RolesAllowed("ROLE_TEACHER")
@Route(value = "teacher", layout = AppLayoutNavbarPlacement.class)
public class MainTeacherView extends VerticalLayout{

    StudentRepository studentRepository;

    Grid<Student> grid = new Grid<>();

    //Grid<User1> grid = new Grid<>(User1.class);
    //List<User1> users = Arrays.asList(
    //        new User1("Darwin", "Nunezm", "darwin@gmail.com"),
    //        new User1("Michael", "Thomas", "michael@gmail.com"),
    //        new User1("Einstein", "Brooks", "einstein@gmail.com")
    //);

    public MainTeacherView(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

        addClassName("Teacher-Main-View");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
        updateList();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();

        grid.addColumn(Student::getFName).setHeader("First Name");
        grid.addColumn(Student::getLName).setHeader("Last Name");
        grid.addColumn(Student::getEmail).setHeader(("Email"));

        grid.getColumns().forEach(col -> col.setAutoWidth(TRUE));

    }

    private HorizontalLayout getToolbar() {
        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(studentRepository.findAll());
    }
}

package Brogrammers.Schooly.views.instructor;

import Brogrammers.Schooly.Entity.Course;
import Brogrammers.Schooly.Repository.CourseRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacement;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

@RolesAllowed("ROLE_TEACHER")
@Route(value = "course", layout = AppLayoutNavbarPlacement.class)
public class CourseView extends VerticalLayout{
    CourseRepository courseRepository;
    Grid<Course> grid = new Grid<>(Course.class);

    public CourseView(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
        addClassName("Course-Teacher-View");
        setSizeFull();
        configureGrid();

        add(grid);

    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setItems(courseRepository.findAll());
    }
}

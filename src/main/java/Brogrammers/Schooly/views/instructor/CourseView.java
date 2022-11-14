package Brogrammers.Schooly.views.instructor;

import Brogrammers.Schooly.Entity.Course;
import Brogrammers.Schooly.Repository.CourseRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacement;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

/**
 * This class is used to construct the Course View for
 * the Instructor user.
 *
 * Last Edited: 11/13/2022
 * Edited By: Andrew Van Es
 */
@RolesAllowed("ROLE_TEACHER")
@Route(value = "course", layout = AppLayoutNavbarPlacement.class)
public class CourseView extends VerticalLayout{

    // Declaring Variables
    CourseRepository courseRepository;
    Grid<Course> grid = new Grid<>(Course.class);

    /**
     * This method is the main method for the CourseView
     * @param courseRepository this is used to reference the courses in the database
     */
    public CourseView(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
        addClassName("Course-Teacher-View");
        setSizeFull();
        configureGrid();

        add(grid);

    }

    /**
     * This method is used to configure/populate
     * the grid of the course view.
     */
    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setItems(courseRepository.findAll());
    }
}

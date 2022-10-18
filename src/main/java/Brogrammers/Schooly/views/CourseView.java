package Brogrammers.Schooly.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import data.Course;

import javax.annotation.security.PermitAll;
import java.util.Arrays;
import java.util.List;

@PermitAll
@Route(value = "course", layout = AppLayoutNavbarPlacement.class)
public class CourseView extends VerticalLayout{

    Grid<Course> grid = new Grid<>(Course.class);
    List<Course> courses = Arrays.asList(
            new Course("ENG-107", "Intro to English Literature", "22"),
            new Course("ENG-108", "Intro to English Literature II", "15"),
            new Course("MAT-103", "Pre-Algebra", "12")
    );

    public CourseView() {
        addClassName("Course-Teacher-View");
        setSizeFull();
        configureGrid();

        add(grid);

    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setItems(courses);
    }
}

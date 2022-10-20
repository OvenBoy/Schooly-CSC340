package Brogrammers.Schooly.views.instructor;

import Brogrammers.Schooly.views.AppLayoutNavbarPlacement;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

import com.vaadin.flow.router.RouteAlias;
import data.entity.User1;

@RolesAllowed("ROLE_TEACHER")
@Route(value = "teacher/", layout = AppLayoutNavbarPlacement.class)
public class MainTeacherView extends VerticalLayout{

    Grid<User1> grid = new Grid<>(User1.class);
    List<User1> users = Arrays.asList(
            new User1("Darwin", "Nunezm", "darwin@gmail.com"),
            new User1("Michael", "Thomas", "michael@gmail.com"),
            new User1("Einstein", "Brooks", "einstein@gmail.com")
    );

    public MainTeacherView() {
        addClassName("Teacher-Main-View");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);

    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setItems(users);
    }

    private HorizontalLayout getToolbar() {
        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}

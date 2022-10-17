package com.schooly.demo.teacher;

import com.schooly.demo.teacher.AppLayoutNavbarPlacement;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import javax.annotation.security.PermitAll;
import java.util.Arrays;
import java.util.List;
import data.User;

@PermitAll
@Route(value = "teacher", layout = AppLayoutNavbarPlacement.class)
public class MainTeacherView extends VerticalLayout{

    Grid<User> grid = new Grid<>(User.class);
    List<User> users = Arrays.asList(
            new User("Darwin", "Nunezm", "darwin@gmail.com"),
            new User("Michael", "Thomas", "michael@gmail.com"),
            new User("Einstein", "Brooks", "einstein@gmail.com")
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

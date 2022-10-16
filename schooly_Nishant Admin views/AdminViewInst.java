package Brogrammers.Schooly.views;


import com.vaadin.flow.component.*;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import data.entity.Instructor;
import data.repository.InstructorRepository;

import javax.management.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Boolean.TRUE;


@PageTitle("Admin-instructor")
@Route(value = "/Admin/instructor")
public class AdminViewInst extends VerticalLayout {
    InstructorRepository instructorRepository;
    Header header = new Header();
    Grid<Instructor> grid = new Grid<>(Instructor.class);
    TextField search = new TextField();
    ModifyFormInst form;
    Binder<Instructor> binder = new BeanValidationBinder<>(Instructor.class);


    public AdminViewInst() throws SQLException, ClassNotFoundException {
        header.add("Schooly");
        setSizeFull();
        gridConfigure();
        formConfigure();
        add(header, toolbarConfigure(), gridForm());
    }
    private Component gridForm() {
        HorizontalLayout gridForm = new HorizontalLayout(grid, form);
        gridForm.setFlexGrow(2, grid);
        gridForm.setFlexGrow(1, form);
        gridForm.setSizeFull();
        return gridForm;
    }

    //Sidebar
    private void formConfigure() {
        form = new ModifyFormInst();
        form.setWidth("25em");
    }

    private Component toolbarConfigure() {
        search.setPlaceholder("Search...");
        search.setClearButtonVisible(true);
        search.setValueChangeMode(ValueChangeMode.LAZY);


        Button studNavigationButton = new Button("Student", event-> UI.getCurrent().navigate("/Admin/student"));
        Button addInstructorButton = new Button("Add Instructor");

        HorizontalLayout toolbar = new HorizontalLayout(studNavigationButton, search, addInstructorButton);


        return toolbar;
    }

    private void gridConfigure() {
        grid.setSizeFull();
        grid.setColumns("instID", "fName", "lName", "email", "courseID");
        grid.getColumns().forEach(col -> col.setAutoWidth(TRUE));
    }
}

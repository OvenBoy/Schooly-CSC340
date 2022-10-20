package Brogrammers.Schooly.views.admin;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import data.entity.Instructor;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;

import static java.lang.Boolean.TRUE;

@RolesAllowed("ROLE_ADMIN")
@PageTitle("Admin-Instructor")
@Route(value = "/Admin/instructor")
public class AdminViewInst extends VerticalLayout {
    ArrayList<Instructor> instructors = new ArrayList<>(5);
    Grid<Instructor> grid = new Grid<>();
    TextField search = new TextField();
    ModifyFormInst form;

    public AdminViewInst() {
        instructors.add(new Instructor(200L, "Darwin", "Nunezm", "darwin@gmail.com", 1));
        instructors.add(new Instructor(201L, "Michael", "Thomas", "michael@gmail.com", 2));
        instructors.add(new Instructor(202L, "Einstein", "Brooks", "einstein@gmail.com", 3));
        instructors.add(new Instructor(203L, "Leonhard", "Euler", "leonhard@gmail.com", 4));
        instructors.add(new Instructor(204L, "William", "Shakespeare", "william@gmail.com", 5));

        setSizeFull();
        gridConfigure();
        formConfigure();
        add(new H1("Schooly"), toolbarConfigure(), gridForm());

        updateGrid();
        closeForm();
    }

    private void closeForm() {
        form.setInstructor(null);
        form.setVisible(false);

    }

    private void updateGrid() {
        grid.setItems(instructors);
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

        form.addListener(ModifyFormInst.SaveEvent.class, this::saveInstructor);
        form.addListener(ModifyFormInst.DeleteEvent.class, this::deleteInstructor);
        form.addListener(ModifyFormInst.CloseEvent.class, e -> closeForm());
    }

    private void deleteInstructor(ModifyFormInst.DeleteEvent event) {
        instructors.remove(event.getInstructor());
        updateGrid();
        closeForm();
    }

    private void saveInstructor(ModifyFormInst.SaveEvent event) {
        instructors.add(event.getInstructor());
        updateGrid();
        closeForm();
    }

    private Component toolbarConfigure() {
        search.setPlaceholder("Search...");
        search.setClearButtonVisible(true);
        search.setValueChangeMode(ValueChangeMode.LAZY);

        Button studNavigationButton = new Button("Student", event-> UI.getCurrent().navigate("/Admin/student"));
        Button addInstructorButton = new Button("Add Instructor");
        addInstructorButton.addClickListener(e -> addInstructor());

        HorizontalLayout toolbar = new HorizontalLayout(studNavigationButton, search, addInstructorButton);

        return toolbar;
    }

    private void addInstructor() {
        grid.asSingleSelect().clear();
        editInstructor(new Instructor());
    }

    private void gridConfigure() {
        grid.setSizeFull();
        grid.addColumn(Instructor::getInstID).setHeader("Instructor ID");
        grid.addColumn(Instructor::getfName).setHeader("First Name");
        grid.addColumn(Instructor::getlName).setHeader("Last Name");
        grid.addColumn(Instructor::getEmail).setHeader("Email");
        grid.addColumn(Instructor:: getCourseID).setHeader("Course ID");
        grid.getColumns().forEach(col -> col.setAutoWidth(TRUE));

        grid.asSingleSelect().addValueChangeListener(e -> editInstructor(e.getValue()));

    }

    private void editInstructor(Instructor instructor) {
        if(instructor == null){
            closeForm();
        }
        else{
            form.setInstructor(instructor);
            form.setVisible(true );
        }
    }
}

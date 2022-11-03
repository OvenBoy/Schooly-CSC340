package Brogrammers.Schooly.views.admin;

import Brogrammers.Schooly.Entity.Instructor;
import Brogrammers.Schooly.Repository.InstructorRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import javax.annotation.security.RolesAllowed;

import static java.lang.Boolean.TRUE;

@PageTitle("Admin-Instructor")
@Route("/Admin/instructor")
@RolesAllowed("ROLE_ADMIN")
public class InstructorList extends VerticalLayout {

    InstructorRepository instructorRepository;
    Grid<Instructor> grid = new Grid<>();

    TextField search = new TextField();
    ModifyFormInstructor form;


    public InstructorList(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
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
        grid.setItems(instructorRepository.findAll());
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
        form = new ModifyFormInstructor();
        form.setWidth("25em");

        form.addListener(ModifyFormInstructor.SaveEvent.class, this::saveInstructor);
        form.addListener(ModifyFormInstructor.DeleteEvent.class, this::deleteInstructor);
        form.addListener(ModifyFormInstructor.CloseEvent.class, e -> closeForm());
    }

    private void deleteInstructor(ModifyFormInstructor.DeleteEvent event) {
        instructorRepository.delete(event.getInstructor());
        updateGrid();
        closeForm();
    }

    private void saveInstructor(ModifyFormInstructor.SaveEvent event) {
        instructorRepository.save(event.getInstructor());
        updateGrid();
        closeForm();
    }

    private Component toolbarConfigure() {
        search.setPlaceholder("Search...");
        search.setClearButtonVisible(true);
        search.setValueChangeMode(ValueChangeMode.LAZY);

        Button studNavigationButton = new Button("Student", event-> UI.getCurrent().navigate("/Admin/student"));
        Button courseNavigationButton = new Button("Course", event-> UI.getCurrent().navigate("/Admin/course"));
        Button addInstructorButton = new Button("Add Instructor");
        addInstructorButton.addClickListener(e -> addInstructor());

        HorizontalLayout toolbar = new HorizontalLayout(studNavigationButton,courseNavigationButton, search, addInstructorButton);

        return toolbar;
    }

    private void addInstructor() {
        grid.asSingleSelect().clear();
        editInstructor(new Instructor());
    }

    private void gridConfigure() {
        grid.setSizeFull();
        grid.addColumn(Instructor::getId).setHeader("Instructor ID");
        grid.addColumn(Instructor::getFName).setHeader("First Name");
        grid.addColumn(Instructor::getLName).setHeader("Last Name");
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

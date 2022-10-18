package Brogrammers.Schooly.views;

import com.vaadin.flow.component.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import data.entity.Student;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;

import static java.lang.Boolean.TRUE;

@RolesAllowed("ROLE_ADMIN")
@PageTitle("Admin-Student")
@Route(value="/Admin/student")
public class AdminViewStud extends VerticalLayout {
    ArrayList<Student> students = new ArrayList<>(5);
    Grid<Student> grid = new Grid<>();
    TextField search = new TextField();
    ModifyFormStud form;

    public AdminViewStud(){

        students.add(new Student(300L,"Claire","Ellison","claire.ellison@schooly.com"));
        students.add(new Student(301L,"Dorothy","White","dorothy.white@schooly.com"));
        students.add(new Student(302L,"Christian","Fraser","christian.fraser@schooly.com"));
        students.add(new Student(303L,"Keith","Marshall","keith.marshall@schooly.com"));
        students.add(new Student(304L,"Paul","Skinner","paul.skinner@schooly.com"));
        students.add(new Student(305L,"Joshua","Taylor","joshua.taylor@schooly.com"));
        students.add(new Student(306L,"Steven","Dowd","steven.dowd@schooly.com"));
        students.add(new Student(307L,"Alexandra","Butler","alexandra.butler@schooly.com"));
        students.add(new Student(308L,"Rachel","Jones","rachel.jones@schooly.com"));
        students.add(new Student(309L,"Stewart","Carr","stewart.carr@schooly.com"));

        setSizeFull();
        gridConfigure();
        formConfigure();
        add(new H1("Schooly"), toolbarConfigure(), gridForm());

        updateGrid();
        closeForm();

    }

    private void closeForm() {
        form.setStudent(null);
        form.setVisible(false);
    }

    private void updateGrid() {
        grid.setItems(students);
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
        form = new ModifyFormStud();
        form.setWidth("25em");

        form.addListener(ModifyFormStud.SaveEvent.class, this::saveStudent);
        form.addListener(ModifyFormStud.DeleteEvent.class, this::deleteStudent);
        form.addListener(ModifyFormStud.CloseEvent.class, e -> closeForm());
    }

    private void deleteStudent(ModifyFormStud.DeleteEvent event) {
        students.remove(event.getStudent());
        updateGrid();
        closeForm();
    }

    private void saveStudent(ModifyFormStud.SaveEvent event) {
        students.add(event.getStudent());
        updateGrid();
        closeForm();
    }

    private Component toolbarConfigure() {
        search.setPlaceholder("Search...");
        search.setClearButtonVisible(true);
        search.setValueChangeMode(ValueChangeMode.LAZY);
        Button instNavigationButton = new Button("Instructor", event-> UI.getCurrent().navigate("/Admin/instructor"));
        Button addStudentButton = new Button("Add Student");
        addStudentButton.addClickListener(e -> addStudent());

        HorizontalLayout toolbar = new HorizontalLayout(instNavigationButton, search, addStudentButton);

        return toolbar;
    }

    private void addStudent() {
        grid.asSingleSelect().clear();
        editStudent(new Student());
    }

    private void gridConfigure() {
        grid.setSizeFull();
        grid.addColumn(Student::getStudID).setHeader("Student ID");
        grid.addColumn(Student::getfName).setHeader("First Name");
        grid.addColumn(Student::getlName).setHeader("Last Name");
        grid.addColumn(Student::getEmail).setHeader("Email");

        grid.getColumns().forEach(col -> col.setAutoWidth(TRUE));

        grid.asSingleSelect().addValueChangeListener(e -> editStudent(e.getValue()));

    }

    private void editStudent(Student student) {
        if(student == null){
            closeForm();
        }
        else{
            form.setStudent(student);
            form.setVisible(true);
        }
    }

}

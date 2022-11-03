package Brogrammers.Schooly.views.admin;

import Brogrammers.Schooly.Entity.Student;
import Brogrammers.Schooly.Repository.StudentRepository;
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

//@RolesAllowed("ROLE_ADMIN")
@PageTitle("Admin-Student")
@Route(value="/Admin/student")
@RolesAllowed("ROLE_ADMIN")
public class StudentList extends VerticalLayout {
    StudentRepository studentRepository;
    Grid<Student> grid = new Grid<>();
    TextField search = new TextField();
    ModifyFormStudent form;

    public StudentList(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
        grid.setItems(studentRepository.findAll());
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
        form = new ModifyFormStudent();
        form.setWidth("25em");

        form.addListener(ModifyFormStudent.SaveEvent.class, this::saveStudent);
        form.addListener(ModifyFormStudent.DeleteEvent.class, this::deleteStudent);
        form.addListener(ModifyFormStudent.CloseEvent.class, e -> closeForm());
    }

    private void deleteStudent(ModifyFormStudent.DeleteEvent event) {
        studentRepository.delete(event.getStudent());
        updateGrid();
        closeForm();
    }

    private void saveStudent(ModifyFormStudent.SaveEvent event) {
        studentRepository.save(event.getStudent());
        updateGrid();
        closeForm();
    }

    private Component toolbarConfigure() {
        search.setPlaceholder("Search...");
        search.setClearButtonVisible(true);
        search.setValueChangeMode(ValueChangeMode.LAZY);
        Button courseNavigationButton = new Button("Course", event-> UI.getCurrent().navigate("/Admin/course"));
        Button instNavigationButton = new Button("Instructor", event-> UI.getCurrent().navigate("/Admin/instructor"));
        Button addStudentButton = new Button("Add Student");
        addStudentButton.addClickListener(e -> addStudent());

        HorizontalLayout toolbar = new HorizontalLayout(instNavigationButton,courseNavigationButton, search, addStudentButton);

        return toolbar;
    }

    private void addStudent() {
        grid.asSingleSelect().clear();
        editStudent(new Student());
    }

    private void gridConfigure() {
        grid.setSizeFull();
        grid.addColumn(Student::getId).setHeader("Student ID");
        grid.addColumn(Student::getFName).setHeader("First Name");
        grid.addColumn(Student::getLName).setHeader("Last Name");
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


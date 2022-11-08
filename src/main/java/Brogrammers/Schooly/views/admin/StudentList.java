package Brogrammers.Schooly.views.admin;

import Brogrammers.Schooly.Entity.Student;
import Brogrammers.Schooly.Repository.StudentRepository;
import Brogrammers.Schooly.views.SecurityService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.json.JSONException;


import javax.annotation.security.RolesAllowed;

import static Brogrammers.Schooly.API.getWeather;
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
    private final SecurityService securityService;

    public StudentList(StudentRepository studentRepository, SecurityService securityService) throws JSONException {
        this.studentRepository = studentRepository;
        this.securityService = securityService;
        setSizeFull();
        gridConfigure();
        formConfigure();
//        String api = "Welcome admin! " + getWeather();
        String api = "Weather";
        HorizontalLayout headers = new HorizontalLayout(new H2("Schooly"), new H6(api));
        headers.setAlignItems(Alignment.BASELINE);
        headers.setSpacing(TRUE);
        headers.setMargin(TRUE);

        add(headers, toolbarConfigure(), gridForm());

        updateGrid();
        closeForm();

    }

    private void closeForm() {
        form.setStudent(null);
        form.setVisible(false);
    }

    private void updateGrid() {
        grid.setItems(studentRepository.search(search.getValue()));
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
        search.addValueChangeListener(e->updateGrid());

        Button courseNavigationButton = new Button("Course", event-> UI.getCurrent().navigate("/Admin/course"));
        Button instNavigationButton = new Button("Instructor", event-> UI.getCurrent().navigate("/Admin/instructor"));
        Button addStudentButton = new Button("Add Student");
        Button logout = new Button("Log out", e -> this.securityService.logout());
        logout.addThemeVariants(ButtonVariant.LUMO_ERROR);
        addStudentButton.addClickListener(e -> addStudent());

        HorizontalLayout toolbar = new HorizontalLayout(instNavigationButton,courseNavigationButton, search, addStudentButton, logout);

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


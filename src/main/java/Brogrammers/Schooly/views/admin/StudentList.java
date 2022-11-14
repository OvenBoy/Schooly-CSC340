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

    public StudentList(StudentRepository studentRepository, SecurityService securityService) {
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

    /**
     * The method hides the form so that the grid can have more space.
     */
    private void closeForm() {
        form.setStudent(null);
        form.setVisible(false);
    }

    /**
     * Updating or populating the grid.
     */
    private void updateGrid() {
        grid.setItems(studentRepository.search(search.getValue()));
    }


    /**
     * This method sets up the form and grid in a horizontal format, form and grid are next to each other.
     */
    private Component gridForm() {
        HorizontalLayout gridForm = new HorizontalLayout(grid, form);
        gridForm.setFlexGrow(2, grid);
        gridForm.setFlexGrow(1, form);
        gridForm.setSizeFull();
        return gridForm;
    }

    /**
     * This is where we instantiate the form.
     */
    private void formConfigure() {
        form = new ModifyFormStudent();
        form.setWidth("25em");

        form.addListener(ModifyFormStudent.SaveEvent.class, this::saveStudent);
        form.addListener(ModifyFormStudent.DeleteEvent.class, this::deleteStudent);
        form.addListener(ModifyFormStudent.CloseEvent.class, e -> closeForm());
    }

    /**
     * This function is used to delete the passed student from the database.
     * @param event
     */
    private void deleteStudent(ModifyFormStudent.DeleteEvent event) {
        studentRepository.delete(event.getStudent());
        updateGrid();
        closeForm();
    }

    /**
     * This function is used to save the passed student into the database.
     * @param event
     */
    private void saveStudent(ModifyFormStudent.SaveEvent event) {
        studentRepository.save(event.getStudent());
        updateGrid();
        closeForm();
    }

    /**
     * This function configures the buttons that are used to navigate through admin accessible views, add and search students.
     */
    private Component toolbarConfigure() {
        search.setPlaceholder("Search...");
        search.setClearButtonVisible(true);
        search.setValueChangeMode(ValueChangeMode.LAZY);
        search.addValueChangeListener(e->updateGrid());

        Button courseNavigationButton = new Button("Course", event-> UI.getCurrent().navigate("/Admin/course"));
        Button instNavigationButton = new Button("Instructor", event-> UI.getCurrent().navigate("/Admin/instructor"));
        Button studCourseNavigationButton = new Button("Student Course", event-> UI.getCurrent().navigate("/Admin/student_course"));
        Button addStudentButton = new Button("Add Student");
        Button logout = new Button("Log out", e -> this.securityService.logout());
        logout.addThemeVariants(ButtonVariant.LUMO_ERROR);
        addStudentButton.addClickListener(e -> addStudent());

        return new HorizontalLayout(instNavigationButton,courseNavigationButton, studCourseNavigationButton, search, addStudentButton, logout);
    }

    /**
     * When the addStudent button is clicked this functioned is called which clears the selected item in grid
     * and calls the editStudent function
     */
    private void addStudent() {
        grid.asSingleSelect().clear();
        editStudent(new Student());
    }

    /**
     * This function configures the grid, setting up the columns and designs.
     * When a grid element is clicked, the form is populated by this function so that we can edit the selected element.
     */
    private void gridConfigure() {
        grid.setSizeFull();
        grid.addColumn(Student::getId).setHeader("Student ID");
        grid.addColumn(Student::getFName).setHeader("First Name");
        grid.addColumn(Student::getLName).setHeader("Last Name");
        grid.addColumn(Student::getEmail).setHeader("Email");

        grid.getColumns().forEach(col -> col.setAutoWidth(TRUE));

        grid.asSingleSelect().addValueChangeListener(e -> editStudent(e.getValue()));

    }

    /**
     * This function populates the form fields with the student that was passed by the calling function.
     * @param student
     */
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


package Brogrammers.Schooly.views.admin;

import Brogrammers.Schooly.Entity.StudentCourse;
import Brogrammers.Schooly.Entity.Take;
import Brogrammers.Schooly.Repository.StudentCourseRepository;
import Brogrammers.Schooly.Repository.TakeRepository;
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

import javax.annotation.security.RolesAllowed;

import static java.lang.Boolean.TRUE;

@PageTitle("Admin-Student-Course")
@Route("/Admin/student_course")
@RolesAllowed("ROLE_ADMIN")
public class StudentCourseAdminView extends VerticalLayout {
    StudentCourseRepository studentCourseRepository;
    TakeRepository takeRepository;
    Grid<StudentCourse> grid = new Grid<>();

    ModifyFormStudentCourse form;
    TextField search = new TextField();
    private SecurityService securityService;


    public StudentCourseAdminView(StudentCourseRepository studentCourseRepository, SecurityService securityService, TakeRepository takeRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.securityService = securityService;
        this.takeRepository = takeRepository;

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
     * This method sets up the form and grid in a vertical format, so on top form and on bottom grid.
     */
    private Component gridForm() {
        VerticalLayout gridForm = new VerticalLayout(form, grid);
        gridForm.setSizeFull();
        return gridForm;

    }

    /**
     * Updating or populating the grid.
     */
    private void updateGrid() {
        grid.setItems(studentCourseRepository.search(search.getValue()));
    }

    /**
     * This function configures the grid, setting up the columns and designs.
     */
    private void gridConfigure(){
        grid.addColumn(StudentCourse::getStudID).setHeader("Student ID");
        grid.addColumn(StudentCourse::getFName).setHeader("First Name");
        grid.addColumn(StudentCourse::getLName).setHeader("Last Name");
        grid.addColumn(StudentCourse::getCourseID).setHeader("Course ID");
        grid.addColumn(StudentCourse::getName).setHeader("Course Name");

        grid.getColumns().forEach(col -> col.setAutoWidth(TRUE));
    }

    /**
     * This function configures the buttons that are used to navigate through admin accessible views, add and search student and classes.
     */
    private Component toolbarConfigure() {
        search.setPlaceholder("Search...");
        search.setClearButtonVisible(true);
        search.setValueChangeMode(ValueChangeMode.LAZY);
        search.addValueChangeListener(e -> updateGrid());

        Button instNavigationButton = new Button("Instructor", event-> UI.getCurrent().navigate("/Admin/instructor"));
        Button studNavigationButton = new Button("Student", event-> UI.getCurrent().navigate("/Admin/student"));
        Button courseNavigationButton = new Button("Course", event-> UI.getCurrent().navigate("/Admin/course"));
        Button addStudentCourse = new Button("Modify List");
        Button logout = new Button("Log out", e -> this.securityService.logout());
        logout.addThemeVariants(ButtonVariant.LUMO_ERROR);
        addStudentCourse.addClickListener(e -> addTake());

        HorizontalLayout toolbar = new HorizontalLayout(instNavigationButton,studNavigationButton,courseNavigationButton, search, addStudentCourse, logout);

        return toolbar;
    }

    /**
     * When the Modify list button is clicked this functioned is called which clears the selected item in grid
     * and calls the editStudent function
     */
    private void addTake() {
        // Clearing the form if populated
        grid.asSingleSelect().clear();
        editStudentCourse(new Take());
    }

    /**
     * This function populates the form fields with the student and their related courses that was passed by the calling function.
     * @param take
     */
    private void editStudentCourse(Take take) {
        if(take == null){
            closeForm();
        }
        else{
            form.setTake(take);
            form.setVisible(true);
        }
    }

    /**
     * Closing the form so that grid is more prominent
     */
    private void closeForm() {
        form.setTake(null);
        form.setVisible(false);
    }

    /**
     * This is where we instantiate the form.
     */
    private void formConfigure() {
        form = new ModifyFormStudentCourse(this.takeRepository);
        form.setWidth("25em");

        form.addListener(ModifyFormStudentCourse.SaveEvent.class, this::saveStudentCourse);
        form.addListener(ModifyFormStudentCourse.DeleteEvent.class, this::deleteStudentCourse);
        form.addListener(ModifyFormStudentCourse.CloseEvent.class, e -> closeForm());
    }

    /**
     * This function is used to delete the passed student-course combo from the database.
     * @param event
     */
    private void deleteStudentCourse(ModifyFormStudentCourse.DeleteEvent event) {
        this.takeRepository.delete(event.getTake());
        updateGrid();
        closeForm();
    }

    /**
     * This function is used to save the passed student-course combo into the database.
     * @param event
     */
    private void saveStudentCourse(ModifyFormStudentCourse.SaveEvent event) {
        this.takeRepository.save(event.getTake());
        updateGrid();
        closeForm();
    }


}

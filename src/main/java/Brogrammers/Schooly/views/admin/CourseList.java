package Brogrammers.Schooly.views.admin;

import Brogrammers.Schooly.Entity.Course;
import Brogrammers.Schooly.Repository.CourseRepository;
import Brogrammers.Schooly.Repository.InstructorRepository;
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

import static Brogrammers.Schooly.APIAndOtherMethods.getWeather;
import static java.lang.Boolean.TRUE;

@PageTitle("Admin-Course")
@Route("/Admin/course")
@RolesAllowed("ROLE_ADMIN")
public class CourseList extends VerticalLayout {
    CourseRepository courseRepository;
    InstructorRepository instructorRepository;
    Grid<Course> grid = new Grid<>();
    TextField search = new TextField();
    ModifyFormCourse form;
    private final SecurityService securityService;


    public CourseList(CourseRepository courseRepository, SecurityService securityService, InstructorRepository instructorRepository) throws JSONException {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
        this.securityService = securityService;
        setSizeFull();
        gridConfigure();
        formConfigure();
        String api = "Welcome admin! " + getWeather();
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
     * This function configures the buttons that are used to navigate through admin accessible views, add and search courses.
     */
    private Component toolbarConfigure() {
        search.setPlaceholder("Search...");
        search.setClearButtonVisible(true);
        search.setValueChangeMode(ValueChangeMode.LAZY);
        search.addValueChangeListener(e-> updateGrid());
        Button instNavigationButton = new Button("Instructor", event-> UI.getCurrent().navigate("/Admin/instructor"));
        Button studNavigationButton = new Button("Student", event-> UI.getCurrent().navigate("/Admin/student"));
        Button studCourseNavigationButton = new Button("Student Course", event-> UI.getCurrent().navigate("/Admin/student_course"));
        Button addCourseButton = new Button("Add Course");
        Button logout = new Button("Log out", e -> this.securityService.logout());
        logout.addThemeVariants(ButtonVariant.LUMO_ERROR);
        addCourseButton.addClickListener(e -> addCourse());

        HorizontalLayout toolbar = new HorizontalLayout(instNavigationButton,studNavigationButton, studCourseNavigationButton, search, addCourseButton, logout);

        return toolbar;
    }

    /**
     * When the addCourse button is clicked this function is called which clears the selected item in grid
     * and calls the editCourse function
     */
    private void addCourse() {
        grid.asSingleSelect().clear();
        editCourse(new Course());
    }

    /**
     * This function populates the form fields with the course that was passed by the calling function.
     * @param course
     */
    private void editCourse(Course course) {
        if(course == null){
            closeForm();
        }
        else{
            form.setCourse(course);
            form.setVisible(true);
        }
    }

    /**
     * This function configures the grid, setting up the columns and designs.
     * When a grid element is clicked, the form is populated by this function so that we can edit the selected element.
     */
    private void gridConfigure() {
        grid.setSizeFull();
        grid.addColumn(Course::getCourseID).setHeader("Course ID");
        grid.addColumn(Course::getName).setHeader("Course Name");

        grid.getColumns().forEach(col -> col.setAutoWidth(TRUE));

        grid.asSingleSelect().addValueChangeListener(e -> editCourse(e.getValue()));
    }

    /**
     * This is where we instantiate the form.
     */
    private void formConfigure() {
        form = new ModifyFormCourse(this.courseRepository, this.instructorRepository);
        form.setWidth("25em");

        form.addListener(ModifyFormCourse.SaveEvent.class, this::saveCourse);
        form.addListener(ModifyFormCourse.DeleteEvent.class, this::deleteCourse);
        form.addListener(ModifyFormCourse.CloseEvent.class, e -> closeForm());
    }

    /**
     * This function is used to save the passed course into the database.
     * @param event
     */
    private void saveCourse(ModifyFormCourse.SaveEvent event) {
        courseRepository.save(event.getCourse());
        updateGrid();
        closeForm();
    }

    /**
     * This function is used to delete the passed course from the database.
     * @param event
     */
    private void deleteCourse(ModifyFormCourse.DeleteEvent event) {
        courseRepository.delete(event.getCourse());
        updateGrid();
        closeForm();
    }

    /**
     * The method hides the form so that the grid can have more space.
     */
    private void closeForm() {
        form.setCourse(null);
        form.setVisible(false);
    }

    /**
     * This function is called to populate the grid.
     */
    private void updateGrid() {
        grid.setItems(courseRepository.search(search.getValue()));
    }

}

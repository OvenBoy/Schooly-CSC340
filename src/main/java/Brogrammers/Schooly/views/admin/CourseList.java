package Brogrammers.Schooly.views.admin;

import Brogrammers.Schooly.Entity.Course;
import Brogrammers.Schooly.Repository.CourseRepository;
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

@PageTitle("Admin-Course")
@Route("/Admin/course")
@RolesAllowed("ROLE_ADMIN")
public class CourseList extends VerticalLayout {
    CourseRepository courseRepository;
    Grid<Course> grid = new Grid<>();
    TextField search = new TextField();
    ModifyFormCourse form;
    private final SecurityService securityService;


    public CourseList(CourseRepository courseRepository, SecurityService securityService) throws JSONException {
        this.courseRepository = courseRepository;
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

    private Component gridForm() {
        VerticalLayout gridForm = new VerticalLayout(form, grid);
        gridForm.setSizeFull();
        return gridForm;
    }

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

    private void addCourse() {
        grid.asSingleSelect().clear();
        editCourse(new Course());
    }

    private void editCourse(Course course) {
        if(course == null){
            closeForm();
        }
        else{
            form.setCourse(course);
            form.setVisible(true);
        }
    }

    private void gridConfigure() {
        grid.setSizeFull();
        grid.addColumn(Course::getId).setHeader("Course ID");
        grid.addColumn(Course::getName).setHeader("Course Name");

        grid.getColumns().forEach(col -> col.setAutoWidth(TRUE));

        grid.asSingleSelect().addValueChangeListener(e -> editCourse(e.getValue()));
    }

    private void formConfigure() {
        form = new ModifyFormCourse();
        form.setWidth("25em");

        form.addListener(ModifyFormCourse.SaveEvent.class, this::saveCourse);
        form.addListener(ModifyFormCourse.DeleteEvent.class, this::deleteCourse);
        form.addListener(ModifyFormCourse.CloseEvent.class, e -> closeForm());
    }

    private void saveCourse(ModifyFormCourse.SaveEvent event) {
        courseRepository.save(event.getCourse());
        updateGrid();
        closeForm();
    }

    private void deleteCourse(ModifyFormCourse.DeleteEvent event) {
        courseRepository.delete(event.getCourse());
        updateGrid();
        closeForm();
    }

    private void closeForm() {
        form.setCourse(null);
        form.setVisible(false);
    }

    private void updateGrid() {
        grid.setItems(courseRepository.search(search.getValue()));
    }
}

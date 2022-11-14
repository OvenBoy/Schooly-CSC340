package Brogrammers.Schooly.views.instructor;

import Brogrammers.Schooly.Entity.Assignment;
import Brogrammers.Schooly.Repository.AssignmentRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacement;
import Brogrammers.Schooly.views.AssigmentForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

import static java.lang.Boolean.TRUE;

/**
 * This is the class that handles the creation of the
 * Assignment Instructor View
 *
 * Last Updated: 11/13/2022
 * Edited By: Andrew Van Es
 */
@RolesAllowed("ROLE_TEACHER")
@Route(value = "/Assignments", layout = AppLayoutNavbarPlacement.class)
public class AssignmentView extends VerticalLayout{

    // Variable Declarations
    AssignmentRepository assignmentRepository;
    Grid<Assignment> grid = new Grid<>(Assignment.class);
    AssigmentForm form;

    /**
     * Main method used to make the view and build the view
     * @param assignmentRepository link to the assignment table in the database
     */
    public AssignmentView(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;

        addClassName("Assignments");
        setSizeFull();
        configureGrid();
        configureForm();
        add(new H1("Assignments"), buttonConfig());

        updateList();
        closeForm();
        add(getContent());
    }

    /**
     * This is used to configure the new assignment button
     * @return the specifications for the button
     */
    public Component buttonConfig() {
        Button newAssignmentButton = new Button("New Assignment");
        newAssignmentButton.addClickListener(e -> addAssignment());

        HorizontalLayout button = new HorizontalLayout(newAssignmentButton);

        return button;
    }

    /**
     * Called to add a new assignment to the grid.
     */
    private void addAssignment() {
        grid.asSingleSelect().clear();
        editAssign(new Assignment());
    }

    /**
     * Simple method used to collapse the form
     */
    private void closeForm() {
        form.setAssignment(null);
        form.setVisible(false);
    }

    /**
     * This method is used to configure the form variable/structure
     */
    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(col -> col.setAutoWidth(TRUE));
        grid.asSingleSelect().addValueChangeListener(e -> editAssign(e.getValue()));
    }

    /**
     * This method is used to specify more aspects when creating the view
     * @return configuration for the views gird and form
     */
    private Component getContent() {
        HorizontalLayout assignment = new HorizontalLayout(grid, form);
        assignment.setFlexGrow(2, grid);
        assignment.setFlexGrow(1, form);
        assignment.addClassNames("content");
        assignment.setSizeFull();
        return assignment;
    }

    /**
     * Method is called when a deleteAssignment event is detected.
     * @param event
     */
    private void delAssign(AssigmentForm.DeleteEvent event) {
        assignmentRepository.delete(event.getAssignment());
        updateList();
        closeForm();
    }

    /**
     * Method is called when a saveAssignment event is detected.
     * @param event
     */
    private void savAssign(AssigmentForm.SaveEvent event) {
        assignmentRepository.save(event.getAssignment());
        updateList();
        closeForm();
    }

    /**
     * Method is called when a editAssignment event is detected.
     * @param assignment
     */
    private void editAssign(Assignment assignment) {
        if (assignment == null) {
            closeForm();
        } else {
            form.setAssignment(assignment);
            form.setVisible(true);
        }
    }

    /**
     * This method is used to configure the form along with
     * its respective buttons
     */
    private void configureForm() {
        form = new AssigmentForm();
        form.setWidth("25em");

        form.addListener(AssigmentForm.SaveEvent.class, this::savAssign);
        form.addListener(AssigmentForm.DeleteEvent.class, this::delAssign);
        form.addListener(AssigmentForm.CloseEvent.class, err -> closeForm() );
    }

    /**
     * This method is used to update the grid in real time.
     */
    private void updateList() {
        grid.setItems(assignmentRepository.search());
    }
}
package Brogrammers.Schooly.views.instructor;

import Brogrammers.Schooly.Entity.StudAssign;
import Brogrammers.Schooly.Repository.StudAssignRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacement;
import Brogrammers.Schooly.views.GradeForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

/**
 * This class is used to create the view for the Instructor grades.
 *
 * Last Edited: 11/13/2022
 * Edited By: Andrew Van Es
 */

@PermitAll
@RolesAllowed("ROLE_TEACHER")
@Route(value = "/StudentGrades", layout = AppLayoutNavbarPlacement.class)
public class GradeView extends VerticalLayout{

    // Declaring Variables
    Grid<StudAssign> grid = new Grid<>(StudAssign.class);
    StudAssignRepository studAssignRepository;
    GradeForm form;

    /**
     * Main method for the grade view
     * @param studAssignRepository used to access the student assignment tables in the database.
     */
    public GradeView(StudAssignRepository studAssignRepository) {
        this.studAssignRepository = studAssignRepository;

        addClassName("Grade-View");
        setSizeFull();
        configureGrid();
        configureForm();
        add(new H1("Student Grades"), buttonConfig());

        updateList();
        closeForm();
        add(getContent());
    }

    /**
     * This method is used to configure the button to make new grade entries
     * @return configured button
     */
    public Component buttonConfig(){
        Button newGradeButton = new Button("New Grade");
        newGradeButton.addClickListener(event -> addGrade());

        return new HorizontalLayout(newGradeButton);
    }

    /**
     * Method for adding new grade entries.
     */
    private void addGrade() {
        grid.asSingleSelect().clear();
        editGrade(new StudAssign());
    }

    /**
     * Method used to collapse the form.
     */
    private void closeForm() {
        form.setStudAssign(null);
        form.setVisible(false);
    }

    /**
     * Method used to configure the form and what data it displays
     */
    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setItems(studAssignRepository.search());
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(ear -> editGrade(ear.getValue()));
    }

    /**
     * This method is used to update the grid with any new entries.
     */
    private void updateList() {
        grid.setItems(studAssignRepository.search());
    }

    /**
     * This method is used to edit grades
     * @param studAssign
     */
    private void editGrade(StudAssign studAssign) {
        if (studAssign == null) {
            closeForm();
        } else {
            form.setStudAssign(studAssign);
            form.setVisible(true);
        }
    }

    /**
     * This method is an event for when an entry is saved.
     * @param eve event variable
     */
    private void savGrade(GradeForm.SavEvent eve) {
        studAssignRepository.save(eve.getStudAssign());
        updateList();
        closeForm();
    }

    /**
     * This method is an event for when an entry is deleted
     * @param eve event variable
     */
    private void delGrade(GradeForm.DelEvent eve) {
        studAssignRepository.delete(eve.getStudAssign());
        updateList();
        closeForm();
    }

    /**
     * This method is used to configure the grid and form and hwo they are displayed
     * @return configuration
     */
    private Component getContent() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(grid, form);
        horizontalLayout.setFlexGrow(2, grid);
        horizontalLayout.setFlexGrow(1, form);
        horizontalLayout.addClassNames("content");
        horizontalLayout.setSizeFull();
        return horizontalLayout;
    }

    /**
     * This method is used to create the form and its buttons
     */
    private void configureForm() {
        form = new GradeForm(this.studAssignRepository);
        form.setWidth("25em");

        form.addListener(GradeForm.SavEvent.class, this::savGrade);
        form.addListener(GradeForm.DelEvent.class, this::delGrade);
        form.addListener(GradeForm.CloseEvent.class, err -> closeForm() );
    }
}
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
import java.awt.*;
import java.util.Collections;

@PermitAll
@RolesAllowed("ROLE_TEACHER")
@Route(value = "/StudentGrades", layout = AppLayoutNavbarPlacement.class)
public class GradeView extends VerticalLayout{

    Grid<StudAssign> grid = new Grid<>(StudAssign.class);
    StudAssignRepository studAssignRepository;
    GradeForm form;

    /*
    Main class for the Grade dashboard
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

    public Component buttonConfig(){
        Button newGradeButton = new Button("New Grade");
        newGradeButton.addClickListener(event -> addGrade());

        HorizontalLayout button = new HorizontalLayout(newGradeButton);
        return button;
    }

    private void addGrade() {
        grid.asSingleSelect().clear();
        editGrade(new StudAssign());
    }

    private void closeForm() {
        form.setStudAssign(null);
        form.setVisible(false);
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setItems(studAssignRepository.search());
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(ear -> editGrade(ear.getValue()));
    }
    private void updateList() {
        grid.setItems(studAssignRepository.search());
    }
    private void editGrade(StudAssign studAssign) {
        if (studAssign == null) {
            closeForm();
        } else {
            form.setStudAssign(studAssign);
            form.setVisible(true);
        }
    }

    private void savGrade(GradeForm.SavEvent eve) {
        studAssignRepository.save(eve.getStudAssign());
        updateList();
        closeForm();
    }

    private void delGrade(GradeForm.DelEvent eve) {
        studAssignRepository.delete(eve.getStudAssign());
        updateList();
        closeForm();
    }

    private Component getContent() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(grid, form);
        horizontalLayout.setFlexGrow(2, grid);
        horizontalLayout.setFlexGrow(1, form);
        horizontalLayout.addClassNames("content");
        horizontalLayout.setSizeFull();
        return horizontalLayout;
    }

    private void configureForm() {
        form = new GradeForm(this.studAssignRepository);
        form.setWidth("25em");

        form.addListener(GradeForm.SavEvent.class, this::savGrade);
        form.addListener(GradeForm.DelEvent.class, this::delGrade);
        form.addListener(GradeForm.CloseEvent.class, err -> closeForm() );
    }
}
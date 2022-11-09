package Brogrammers.Schooly.views.instructor;

import Brogrammers.Schooly.Entity.StudAssign;
import Brogrammers.Schooly.Repository.StudAssignRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacement;
import Brogrammers.Schooly.views.GradeForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.Collections;

@PermitAll
@RolesAllowed("ROLE_TEACHER")
@Route(value = "/StudentGrades", layout = AppLayoutNavbarPlacement.class)
public class GradeView extends VerticalLayout{

    Grid<StudAssign> grid = new Grid<>(StudAssign.class);
    GradeForm form;
    StudAssignRepository studAssignRepository;

    /*
    Main class for the Grade dashboard
     */
    public GradeView(StudAssignRepository studAssignRepository) {
        this.studAssignRepository = studAssignRepository;
        addClassName("Grade-View");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getContent());
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setItems(studAssignRepository.search());
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
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
        form = new GradeForm(Collections.emptyList());
        form.setWidth("25em");
    }
}

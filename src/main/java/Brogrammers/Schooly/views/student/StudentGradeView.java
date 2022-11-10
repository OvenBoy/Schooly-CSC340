package Brogrammers.Schooly.views.student;


import Brogrammers.Schooly.Entity.StudAssign;
import Brogrammers.Schooly.Repository.StudAssignRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacementStudent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import javax.annotation.security.RolesAllowed;

@Route(value = "grades", layout = AppLayoutNavbarPlacementStudent.class)
@PageTitle("Grades | Schooly")
@RolesAllowed("ROLE_STUDENT")
public class StudentGradeView extends VerticalLayout {
    protected Grid<StudAssign> grid = new Grid<>(StudAssign.class, false);
    protected H2 currentPage = new H2("Grades");
    StudAssignRepository studAssignRepository;

    public StudentGradeView(StudAssignRepository studAssignRepository) {
        this.studAssignRepository = studAssignRepository;
        addClassName("student-grade-view");
        setSizeFull();
        configureGrid();

        add(currentPage, new Hr(), grid);
        updateGrid();

    }
    private void configHeader() {
        HorizontalLayout header = new HorizontalLayout(
                currentPage
        );
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER
        );
        header.setAlignSelf(FlexComponent.Alignment.AUTO);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-s");
    }

    private void configureGrid() {
        grid.addClassName("grade-grid");
        grid.setSizeFull();

        grid.addColumn(StudAssign::getName).setHeader("Assignment");
        grid.addColumn(StudAssign::getGrade).setHeader("Grade");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setSortableColumns();
        grid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
    }
    private void updateGrid() {
        grid.setItems(studAssignRepository.searchStud());
    }

}

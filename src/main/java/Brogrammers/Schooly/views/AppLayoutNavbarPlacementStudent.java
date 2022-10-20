package Brogrammers.Schooly.views;

import Brogrammers.Schooly.views.student.MainStudentView;
import Brogrammers.Schooly.views.student.StudentAssignmentView;
import Brogrammers.Schooly.views.student.StudentGradeView;
import Brogrammers.Schooly.views.student.StudentToDoView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class AppLayoutNavbarPlacementStudent extends AppLayout {

    private final SecurityService securityService;

    public AppLayoutNavbarPlacementStudent(SecurityService securityService){
        this.securityService = securityService;
        createHeader();
    }

    protected void createHeader(){
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Schooly");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");
        Button logout = new Button("Log out", e -> securityService.logout());
        HorizontalLayout layout = new HorizontalLayout(toggle, title, logout);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        layout.expand(title);
        layout.setWidth("97%");
        layout.addClassNames("py-0", "px-m");

        Tabs tabs = getTabs();

        addToDrawer(tabs);
        addToNavbar(layout);
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.add(createTab1(VaadinIcon.USER, "Dashboard"),
                createTab2(VaadinIcon.NOTEBOOK, "Assignments"),
                createTab3(VaadinIcon.CHART, "Grades"),
                createTab4(VaadinIcon.TASKS, "To-Do")
        );
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab1(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(MainStudentView.class);
        link.setTabIndex(-1);

        return new Tab(link);
    }

    /*
    AssignmentView Page
     */
    private Tab createTab2(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(StudentAssignmentView.class);
        link.setTabIndex(-1);

        return new Tab(link);
    }

    /*
    GradeView Page
     */
    private Tab createTab3(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(StudentGradeView.class);
        link.setTabIndex(-1);

        return new Tab(link);
    }

    private Tab createTab4(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(StudentToDoView.class);
        link.setTabIndex(-1);

        return new Tab(link);
    }

}

package Brogrammers.Schooly.views;

import Brogrammers.Schooly.views.instructor.AssignmentView;
import Brogrammers.Schooly.views.instructor.CourseView;
import Brogrammers.Schooly.views.instructor.GradeView;
import Brogrammers.Schooly.views.instructor.MainTeacherView;
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

import javax.annotation.security.PermitAll;

/*
Creates the Navigation bar for the App
 */
@PermitAll
public class AppLayoutNavbarPlacement extends AppLayout {
    private final SecurityService securityService;

    public AppLayoutNavbarPlacement(SecurityService securityService) {
        this.securityService = securityService;
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Schooly Teacher View");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");
        Button logout = new Button("Log out", e -> securityService.logout());
        HorizontalLayout config = new HorizontalLayout(toggle, title, logout);
        config.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        config.expand(title);
        config.setWidth("97%");

        Tabs tabs = getTabs();

        addToDrawer(tabs);
        addToNavbar(config);
    }

    /*
    Creates NavTabs
     */
    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.add(createTab1(VaadinIcon.USER, "Students"),
                createTab2(VaadinIcon.LIST, "Assignments"),
                createTab3(VaadinIcon.CHART, "Grades"),
                createTab4(VaadinIcon.ARCHIVE, "Courses")
                );

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    /*
    MainTeacherView Page
     */
    private Tab createTab1(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(MainTeacherView.class);
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
        link.setRoute(AssignmentView.class);
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
        link.setRoute(GradeView.class);
        link.setTabIndex(-1);

        return new Tab(link);
    }

    /*
    Course Teacher page
     */
    private Tab createTab4(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(CourseView.class);
        link.setTabIndex(-1);

        return new Tab(link);
    }
}


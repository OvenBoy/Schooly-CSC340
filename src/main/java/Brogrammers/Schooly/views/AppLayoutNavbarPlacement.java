package Brogrammers.Schooly.views;

import Brogrammers.Schooly.views.instructor.AssignmentView;
import Brogrammers.Schooly.views.instructor.CourseView;
import Brogrammers.Schooly.views.instructor.GradeView;
import Brogrammers.Schooly.views.instructor.MainTeacherView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import org.json.JSONException;

import javax.annotation.security.PermitAll;

/**
 * This Class is used to create the navbar for the instructor views.
 *
 * Last Edited: 11/14/2022
 * Edited By: Andrew Van Es
 */
@PermitAll
public class AppLayoutNavbarPlacement extends AppLayout {
    private final SecurityService securityService;

    /**
     * This is the main method for the class
     * @param securityService security service information
     */
    public AppLayoutNavbarPlacement(SecurityService securityService) {
        this.securityService = securityService;
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Schooly Teacher View ");
        H2 weather = new H2("Weather");
        //H1 weather = new H1(getWeather());

        title.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");
        weather.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");

        Button logout = new Button("Log out", e -> securityService.logout());
        HorizontalLayout config = new HorizontalLayout(toggle, title, weather, logout);
        config.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        config.expand(weather);
        config.setWidth("97%");

        Tabs tabs = getTabs();

        addToDrawer(tabs);
        addToNavbar(config);
    }

    /**
     * This method is used to set up the navigation tabs for the nav bar
     * @return tabs configuration
     */
    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.add(createTab1(),
                createTab2(),
                createTab3(),
                createTab4()
        );

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    /**
     * This method is used to set up the main teacher page link
     *
     * @return configured link
     */
    private Tab createTab1() {
        Icon icon = VaadinIcon.USER.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span("Students"));
        link.setRoute(MainTeacherView.class);
        link.setTabIndex(-1);

        return new Tab(link);
    }

    /**
     * This method is used to set up the AssignmentView link
     *
     * @return configured link
     */
    private Tab createTab2() {
        Icon icon = VaadinIcon.LIST.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span("Assignments"));
        link.setRoute(AssignmentView.class);
        link.setTabIndex(-1);

        return new Tab(link);
    }

    /**
     * This method is used to set up the GradeView link
     *
     * @return configured link
     */
    private Tab createTab3() {
        Icon icon = VaadinIcon.CHART.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span("Grades"));
        link.setRoute(GradeView.class);
        link.setTabIndex(-1);

        return new Tab(link);
    }

    /**
     * This method is used to set up the CourseView link
     *
     * @return configured link
     */
    private Tab createTab4() {
        Icon icon = VaadinIcon.ARCHIVE.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span("Courses"));
        link.setRoute(CourseView.class);
        link.setTabIndex(-1);

        return new Tab(link);
    }
}


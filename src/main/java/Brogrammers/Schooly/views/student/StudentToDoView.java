package Brogrammers.Schooly.views.student;

import Brogrammers.Schooly.views.AppLayoutNavbarPlacementStudent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@Route(value ="to-do", layout = AppLayoutNavbarPlacementStudent.class)
@PageTitle("To-Do | Schooly")
@RolesAllowed("ROLE_STUDENT")
public class StudentToDoView extends VerticalLayout {

    H2 currentPage = new H2("To-Do");
    VerticalLayout todosList = new VerticalLayout();

    public StudentToDoView(){
        addClassName("list-view");
        setSizeFull();
        configHeader();

        TextField taskField = new TextField();
        Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            Checkbox checkbox = new Checkbox(taskField.getValue());
            todosList.add(checkbox);
        });
        addButton.addClickShortcut(Key.ENTER);

        Div container = new Div(todosList);
        setContainerStyles(container);

        add(
                currentPage,
                new Hr(),
                new HorizontalLayout(
                        taskField,
                        addButton
                ),
                container
        );

    }

    private void setContainerStyles(Div container) {
        container.getStyle().set("display", "flex").set("flex-direction", "row")
             .set("flex-wrap", "wrap");

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
}

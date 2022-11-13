package Brogrammers.Schooly.views.student;


import Brogrammers.Schooly.Entity.ToDoStudent;
import Brogrammers.Schooly.Repository.ToDoStudentRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacementStudent;

import Brogrammers.Schooly.views.ToDoForm;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import javax.annotation.security.RolesAllowed;

@Route(value ="to-do", layout = AppLayoutNavbarPlacementStudent.class)
@PageTitle("To-Do  | Schooly")
@RolesAllowed("ROLE_STUDENT")
public class StudentToDoView extends VerticalLayout {
    private ToDoStudent todo;

    protected H2 currentPage = new H2("To-Do");
    protected VerticalLayout todosList = new VerticalLayout();
    Grid<ToDoStudent> grid = new Grid<>(ToDoStudent.class, false);

    ToDoForm form;
    ToDoStudentRepository todoRepo;

    public StudentToDoView(ToDoStudentRepository todoRepo) {

        this.todoRepo = todoRepo;

        addClassName("list-view");
        setSizeFull();
        configHeader();
        configGrid();

        TextField taskField = new TextField();
        Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            taskField.getValue();
            addToDo();

        });
        addButton.addClickShortcut(Key.ENTER);


        add(
                currentPage,
                new Hr(),
                new HorizontalLayout(
                        taskField,
                        addButton
                ),
                grid
        );
        updateGrid();
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

    private void configGrid() {
        grid.addClassName("todo-grid");
        grid.setSizeFull();
        grid.addColumn(ToDoStudent::getItemName).setHeader("To-Do");
        grid.addColumn(ToDoStudent::isStatus).setHeader("Status");
        ComboBox<ToDoStudent> status = new ComboBox<>("Status");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }

    private void updateGrid() {
        grid.setItems(todoRepo.findAll());


    }
    private void closeForm() {
        form.setToDo(null);
        form.setVisible(false);
    }
    private void addToDo() {
        grid.asSingleSelect().clear();
        editAssign(new ToDoStudent());
    }

    private void editAssign(ToDoStudent todo) {
        if (todo == null) {
            closeForm();
        } else {
            form.setToDo(todo);
            form.setVisible(true);
        }
    }

}


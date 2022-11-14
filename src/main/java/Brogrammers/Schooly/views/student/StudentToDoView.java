package Brogrammers.Schooly.views.student;


import Brogrammers.Schooly.Entity.ToDoStudent;
import Brogrammers.Schooly.Repository.ToDoStudentRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacementStudent;

import Brogrammers.Schooly.views.ToDoForm;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Route(value ="to-do", layout = AppLayoutNavbarPlacementStudent.class)
@PageTitle("To-Do  | Schooly")
@RolesAllowed("ROLE_STUDENT")
public class StudentToDoView extends VerticalLayout {
    private ToDoStudent todo;
    Binder<ToDoStudent> binder = new BeanValidationBinder<>(ToDoStudent.class);

    protected H2 currentPage = new H2("To-Do");
    protected VerticalLayout todosList = new VerticalLayout();
    Grid<ToDoStudent> grid = new Grid<>(ToDoStudent.class, false);
    Editor<ToDoStudent> editor = grid.getEditor();

    ToDoForm form;
    ToDoStudentRepository todoRepo;

    public StudentToDoView(ToDoStudentRepository todoRepo) {
        form = new ToDoForm();
        this.todoRepo = todoRepo;

        addClassName("list-view");
        setSizeFull();
        formConfigure();
        configHeader();
        configGrid();

        add(
                currentPage,
                new Hr(),
                form,
                grid
        );
        updateGrid();
    }
    private void setContainerStyles(Div container) {
        container.getStyle().set("display", "flex").set("flex-direction", "row")
                .set("flex-wrap", "wrap");

    }

    private void setTodo(ToDoStudent todo){
        this.todo = todo;
        binder.readBean(todo);
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
        grid.addColumn(
                new ComponentRenderer<>(
                        todo -> {
                            Checkbox checkbox = new Checkbox();
                            checkbox.setValue(todo.isStatus());
                            checkbox.addValueChangeListener(e->{
                                setTodo(todo);
                                todo.setStatus(checkbox.getValue());
                                this.todoRepo.save(todo);
                                updateGrid();
                            });
                            return checkbox;
                        }

                )
        ).setHeader("Completed").setKey("hasFiles");

       

        grid.addColumn(ToDoStudent::isStatus).setHeader("Status");
        //status will be removed, need for data-binding debug


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

    private void formConfigure(){
        form = new ToDoForm();
        form.addListener(ToDoForm.SaveEvent.class, this::saveTodo);
    }

    private void saveTodo(ToDoForm.SaveEvent event) {
        todoRepo.save(event.getToDo());
        updateGrid();
    }

}


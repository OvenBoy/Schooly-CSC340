package Brogrammers.Schooly.views.student;


import Brogrammers.Schooly.Entity.ToDoStudent;
import Brogrammers.Schooly.Repository.ToDoStudentRepository;
import Brogrammers.Schooly.views.AppLayoutNavbarPlacementStudent;

import Brogrammers.Schooly.views.ToDoForm;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.*;

import javax.annotation.security.RolesAllowed;

/**
 * This view creates the entire to-do page. It creates, validates, deletes, and 
 * edits to-do entries. 
 * @author evanc
 */
@Route(value ="to-do", layout = AppLayoutNavbarPlacementStudent.class)
@PageTitle("To-Do  | Schooly")
@RolesAllowed("ROLE_STUDENT")
public class StudentToDoView extends VerticalLayout {
    private ToDoStudent todo;
    TextField ToDoField = new TextField();
    Binder<ToDoStudent> binder = new BeanValidationBinder<>(ToDoStudent.class);

    /**
     * H2 to display title of current page
     */
    protected H2 currentPage = new H2("To-Do");

    /**
     * VerticalLayout for list items
     */
    protected VerticalLayout todosList = new VerticalLayout();
    Grid<ToDoStudent> grid = new Grid<>(ToDoStudent.class, false);
    Editor<ToDoStudent> editor = grid.getEditor();

    ToDoForm form;
    ToDoStudentRepository todoRepo;

    /**
     * This main function of the class initializes a form and instantiates  
     * a version of todoRepo. Then it configures the form, header, and grid
     * and adds them to the current page. It will then populate the grid with
     * all information from the database
     * @param todoRepo repository of students to-do items
     */
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
        Grid.Column<ToDoStudent> ToDoItem = grid.addColumn(ToDoStudent::getItemName).setHeader("To-Do");
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


        ToDoField.setWidthFull();
        //Delete Button Action
        Grid.Column<ToDoStudent> editColumn = grid.addComponentColumn(todo -> {
            Button delete = new Button(VaadinIcon.CLOSE.create());
            delete.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR);
            delete.addClickListener(e -> {
                this.todoRepo.delete(todo);
                updateGrid();
            });
            return delete;
        }).setWidth("150px").setFlexGrow(0);

        Binder<ToDoStudent> binder = new Binder<>(ToDoStudent.class);
        editor.setBinder(binder);
        binder.forField(ToDoField).bind(ToDoStudent::getItemName, ToDoStudent::setItemName);
        addCloseHandler(ToDoField, editor);
        ToDoItem.setEditorComponent(ToDoField);
        //Edit Item
        grid.addItemDoubleClickListener(e -> {
            editAssign(e.getItem());
            editor.editItem(e.getItem());
            Component editorComponent = e.getColumn().getEditorComponent();
            if (editorComponent instanceof Focusable) {
                ((Focusable) editorComponent).focus();
            }
        });
        
        ToDoField.addValueChangeListener(e->{
            todo.setItemName(ToDoField.getValue());
            this.todoRepo.save(todo);
            updateGrid();
        });

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }


    private void updateGrid() {
        grid.setItems(todoRepo.findAll());
    }
    private void closeForm() {
        form.setToDo(null);
        form.setVisible(false);
    }

    private void editAssign(ToDoStudent todo) {
        if (todo == null) {
            return;
        } else {
            this.todo = todo;
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

    private static void addCloseHandler(Component textField,
                                        Editor<ToDoStudent> editor) {
        textField.getElement().addEventListener("keydown", e -> editor.cancel())
                .setFilter("event.code === 'Enter'");
    }

}


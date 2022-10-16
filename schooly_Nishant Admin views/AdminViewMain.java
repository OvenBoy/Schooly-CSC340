package Brogrammers.Schooly.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import data.entity.Instructor;
import data.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.TRUE;

@Route(value="/Admin")
public class AdminViewMain extends VerticalLayout {
    InstructorRepository instructorRepository;
    Header header = new Header();
    Grid<Instructor> grid = new Grid<>(Instructor.class);
    TextField search = new TextField();
    TextField instID = new TextField("Instructor ID");
    TextField fName = new TextField("First name");
    TextField lName = new TextField("Last name");
    EmailField email = new EmailField("Email");

    TextField courseID = new TextField("Course ID");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    Binder<Instructor> binder = new Binder<>(Instructor.class);

    public AdminViewMain(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
        setSizeFull();
        header.add("Schooly");
        grid.setSizeFull();
        grid.setColumns("instID", "fName", "lName", "email", "courseID");
        grid.getColumns().forEach(col -> col.setAutoWidth(TRUE));
        add(header, toolbarConfigure());
        add(form(),grid);

    }

    private Component form(){
        var layout = new HorizontalLayout();
        layout.setAlignItems(Alignment.BASELINE);
        layout.add(instID, fName, lName, email, courseID, editButtons());

        binder.bindInstanceFields(this);
        save.addClickListener(click ->{
            try{
                var instructor = new Instructor();
                binder.writeBean(instructor);
                instructorRepository.save(instructor);
                binder.readBean(new Instructor());
                refreshGrid();

            } catch(ValidationException e){

            }
        });
        return layout;
    }

    private void refreshGrid() {
        grid.setItems(instructorRepository.findAll());
    }

    private HorizontalLayout editButtons() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);

    }
    private Component toolbarConfigure() {
        search.setPlaceholder("Search...");
        search.setClearButtonVisible(true);
        search.setValueChangeMode(ValueChangeMode.LAZY);


        Button instNavigationButton = new Button("Student", event-> UI.getCurrent().navigate("/Admin/student"));

        HorizontalLayout toolbar = new HorizontalLayout(instNavigationButton, search);
        toolbar.setAlignItems(Alignment.CENTER);


        return toolbar;
    }

}

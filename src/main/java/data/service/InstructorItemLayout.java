package data.service;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import data.entity.Instructor;

import java.util.Collection;

public class InstructorItemLayout extends HorizontalLayout {

    public InstructorItemLayout(Instructor instructor) {

        Binder<Instructor> binder = new Binder<>(Instructor.class);
        binder.bindInstanceFields(this);
        binder.setBean(instructor);

    }
}

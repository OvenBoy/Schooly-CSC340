package Brogrammers.Schooly.views;

import Brogrammers.Schooly.Entity.StudAssign;
import Brogrammers.Schooly.Entity.ToDoStudent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;


public class ToDoForm extends FormLayout {

    private ToDoStudent todo;

    Binder<ToDoStudent> bind = new BeanValidationBinder<>(ToDoStudent.class);

    //Text Fields
    TextField itemName = new TextField("Item Name");
//    TextField ID = new TextField("ID");
//    TextField status = new TextField("Status");



    public ToDoForm() {
        bind.bindInstanceFields(this);
    }

    public void setToDo(ToDoStudent todo) {
        this.todo = todo;
        bind.readBean(todo);

    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
package Brogrammers.Schooly.views;

import Brogrammers.Schooly.Entity.StudAssign;
import Brogrammers.Schooly.Entity.Student;
import Brogrammers.Schooly.Entity.ToDoStudent;
import Brogrammers.Schooly.views.admin.ModifyFormStudent;
import Brogrammers.Schooly.views.student.StudentToDoView;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;


public class ToDoForm extends FormLayout {
    Binder<ToDoStudent> bind = new BeanValidationBinder<>(ToDoStudent.class);

    //Text Fields
    TextField itemName = new TextField("Item Name");
    Button save = new Button("Add");
    private ToDoStudent toDoStudent;

    public ToDoForm() {
        add(itemName, save);
        save.addClickListener(event-> save());
        //BIND TO DATABASE
        //bind.bind(checkbox, ToDoStudent::isStatus, ToDoStudent::setStatus);
    }


    public void setToDo(ToDoStudent toDoStudent) {
        this.toDoStudent = toDoStudent;
        bind.readBean(toDoStudent);
    }

    private void save() {
        toDoStudent = new ToDoStudent(this.itemName.getValue());
        this.itemName.setValue("");
        if(toDoStudent.getItemName() == null){
            System.out.println("Item is null");
            return;
        }
        fireEvent(new ToDoForm.SaveEvent(this, toDoStudent));
    }





    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
    public static abstract class ToDoFormEvent extends ComponentEvent<ToDoForm> {
        private ToDoStudent toDoStudent;

        protected ToDoFormEvent(ToDoForm source, ToDoStudent toDoStudent) {
            super(source, false);
            this.toDoStudent = toDoStudent;
        }

        public ToDoStudent getToDo() {
            return toDoStudent;
        }
    }

    public static class SaveEvent extends ToDoForm.ToDoFormEvent {
        SaveEvent(ToDoForm source, ToDoStudent toDoStudent) {
            super(source, toDoStudent);
        }
    }

    public static class CheckBox{

    }
    public void checkBox(){
        //bind.bind(checkbox, booleanValueProvider, booleanSetter);
    }
}
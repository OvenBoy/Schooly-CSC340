package Brogrammers.Schooly.views;

import Brogrammers.Schooly.Entity.StudAssign;
import Brogrammers.Schooly.Entity.Student;
import Brogrammers.Schooly.Entity.ToDoStudent;
import Brogrammers.Schooly.views.admin.ModifyFormStudent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
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

    private ToDoStudent todo;

    Binder<ToDoStudent> bind = new BeanValidationBinder<>(ToDoStudent.class);

    //Text Fields
    TextField itemName = new TextField("Item Name");
    Button addButton = new Button("Add");
//    TextField itemName = new TextField("Item Name");
//    TextField ID = new TextField("ID");
//    TextField status = new TextField("Status");

    public ToDoForm() {
       add(itemName, addButton);
        bind.bindInstanceFields(this.itemName);
        addButton.addAttachListener(event-> save());

    }

    public void setToDo(ToDoStudent todo) {
        this.todo = todo;
        bind.readBean(todo);
    }

    private void save() {
        try {
            bind.writeBean(todo);
            if(todo.getItemName().isEmpty()){
                //Try using notification just like I did;
                return;
            }
            fireEvent(new ToDoForm.SaveEvent(this, todo));
        }catch (ValidationException e){
            throw new RuntimeException(e);
        }


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

}
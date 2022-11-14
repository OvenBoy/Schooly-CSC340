package Brogrammers.Schooly.views;

import Brogrammers.Schooly.Entity.StudAssign;
import Brogrammers.Schooly.Entity.Student;
import Brogrammers.Schooly.Entity.ToDoStudent;
import Brogrammers.Schooly.views.admin.ModifyFormStudent;
import Brogrammers.Schooly.views.student.StudentToDoView;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
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
    }


    public void setToDo(ToDoStudent toDoStudent) {
        this.toDoStudent = toDoStudent;
        bind.readBean(toDoStudent);
    }

    private void save() {
        toDoStudent = new ToDoStudent(this.itemName.getValue());
        this.itemName.setValue("");
        if(toDoStudent.getItemName() == null || toDoStudent.getItemName() == ""){
            System.out.println("Item is null");
            Notification notification = show();
            notification
                    .addDetachListener(detachEvent -> save.setEnabled(true));
            return;
        }
        fireEvent(new ToDoForm.SaveEvent(this, toDoStudent));
    }

    public Notification show(){
        Div text = new Div(new Text("To Do Item Empty"));


        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });
        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        notification.add(layout);
        notification.open();

        notification.setPosition(Notification.Position.MIDDLE);

        return notification;
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
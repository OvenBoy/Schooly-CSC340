package Brogrammers.Schooly.views;

import Brogrammers.Schooly.views.admin.AdminViewInst;
import Brogrammers.Schooly.views.instructor.MainTeacherView;
import Brogrammers.Schooly.views.student.MainStudentView;
import Brogrammers.Schooly.views.student.StudentToDoView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.awt.*;

@Route("login")
@PageTitle("Login | Schooly")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
	private final LoginForm login = new LoginForm();

	public LoginView() {
		addClassName("login-view");
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);

		login.setAction("login");

		login.addLoginListener(e -> {
			if("admin".equals(e.getUsername())){
				UI.getCurrent().navigate(AdminViewInst.class);
			}else if("instructor".equals(e.getUsername())){
				UI.getCurrent().navigate(MainTeacherView.class);
			} else if("student".equals(e.getUsername())){
				UI.getCurrent().navigate(MainStudentView.class);
			}else{
				Notification.show("Wrong");
			}
		});

		add(new H1("Schooly"), login);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		// inform the user about an authentication error
		if (beforeEnterEvent.getLocation()
				.getQueryParameters()
				.getParameters()
				.containsKey("error")) {
			login.setError(true);
		}

	}

}
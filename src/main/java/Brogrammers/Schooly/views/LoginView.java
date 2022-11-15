package Brogrammers.Schooly.views;

import Brogrammers.Schooly.views.admin.InstructorList;
import Brogrammers.Schooly.views.instructor.MainTeacherView;
import Brogrammers.Schooly.views.student.MainStudentView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;

/**
 * This Class is the constructor for the login page. which handles the
 * creation of the view
 *
 * Last Edited: 11/14/2022
 * Edited By: Andrew Van Es
 */
@Route("login")
@PageTitle("Login | Schooly")
@AnonymousAllowed

public class LoginView extends VerticalLayout implements BeforeEnterObserver {
	private final LoginForm login = new LoginForm();

	/**
	 * This is the main method for the LoginView
	 */
	public LoginView() {
		addClassName("login-view");
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);


		login.setAction("login");

		// checks which user is logged in
		login.addLoginListener(e -> {
			if("admin".equals(e.getUsername())){
				UI.getCurrent().navigate(InstructorList.class);
			}else if("instructor".equals(e.getUsername())){
				UI.getCurrent().navigate(MainTeacherView.class);
			} else if("student".equals(e.getUsername())){
				UI.getCurrent().navigate(MainStudentView.class);
			}else{
				Notification.show("Wrong");
			}
		});

		HorizontalLayout logo = new HorizontalLayout();
		logo.setId("logo");
		Image img = new Image("images/logo.svg", "Schooly Logo");
		H1 title = new H1("Schooly");
		title.getStyle().set("vertical-align", "baseline");
		img.setMaxHeight("50%");
		logo.add(img, title);

		logo.getThemeList().add("spacing-xs");
	    logo.setJustifyContentMode(JustifyContentMode.EVENLY);
		logo.setDefaultVerticalComponentAlignment(Alignment.END);
		setPadding(false);
		logo.setWidth("Auto");

		add(logo, login);
	}

	/**
	 * This method is used for an verification event if the user enters
	 * the wrong credentials
	 * @param beforeEnterEvent
	 */
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
package Brogrammers.Schooly.views;

import java.util.Collections;

import Brogrammers.Schooly.views.LoginView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.vaadin.flow.spring.security.VaadinWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {
    static SimpleGrantedAuthority teacher = new SimpleGrantedAuthority("ROLE_TEACHER");
    static SimpleGrantedAuthority admin = new SimpleGrantedAuthority("ROLE_ADMIN");
    static SimpleGrantedAuthority student = new SimpleGrantedAuthority("ROLE_STUDENT");

    private static class SimpleInMemoryUserDetailsManager extends InMemoryUserDetailsManager {


        public SimpleInMemoryUserDetailsManager() {
            createUser(new User("instructor", "{noop}userpass", Collections.singleton(teacher)));
            createUser(new User("admin", "{noop}userpass", Collections.singleton(admin)));
            createUser(new User("student", "{noop}userpass", Collections.singleton(student)));

        }


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/images/**").permitAll();

        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new SimpleInMemoryUserDetailsManager();
    }

    public static String getTeacherRole(){
        return teacher.getAuthority();
    }
    public static String getAdminRole(){
        return admin.getAuthority();
    }
    public static String getStudentRole(){
        return student.getAuthority();
    }
}
package data.service;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import data.entity.Instructor;
import data.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class InstructorLayout extends VerticalLayout {
    @Autowired
    InstructorRepository instructorRepository;

    @PostConstruct
    void init(){
        setInstructors(instructorRepository.findAll());
    }

    private void setInstructors(List<Instructor> instructors) {
        removeAll();

        instructors.forEach(instructor -> add(new InstructorItemLayout(instructor)));
    }

}

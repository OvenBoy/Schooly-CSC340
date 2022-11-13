package data.entity;

import Brogrammers.Schooly.Entity.Assignment;
import Brogrammers.Schooly.Entity.AssignmentStudentview;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.stream.Stream;

public class Stu_AssignmentDetailsFormLayout extends FormLayout {
        private final TextField courseTitle = new TextField("Course Title");
        private final TextField assignmentTitle = new TextField("Assignment");
        private final TextField dueDate = new TextField("Due Date");
        private final TextField description = new TextField("Description");
        private final TextField possPoints = new TextField("Possible Points");

        public Stu_AssignmentDetailsFormLayout(){
                Stream.of(courseTitle, assignmentTitle, dueDate, description)
                        .forEach(field -> {
                                field.setReadOnly(true);
                                add(field);
                        });
                setResponsiveSteps(new ResponsiveStep("0", 3));
                setColspan(courseTitle, 1);
                setColspan(assignmentTitle, 1);
                setColspan(description, 3);
        }

        public void setAssignment(AssignmentStudentview assignment){
                courseTitle.setValue(assignment.getCourseName());
                assignmentTitle.setValue(assignment.getAssignmentName());
                dueDate.setValue(assignment.getDueDate().toString());
                description.setValue(assignment.getDescription());
                //time.setValue(assignment.getTime());
                //possPoints.setValue(assignment.getPossiblePoints());

        }

}

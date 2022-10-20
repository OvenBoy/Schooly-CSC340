package data.entity;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.stream.Stream;

public class Stu_AssignmentDetailsFormLayout extends FormLayout {
        private final TextField courseTitle = new TextField("Course Title");
        private final TextField assignmentTitle = new TextField("Assignment");
        private final TextField dueDate = new TextField("Due Date");
        private final TextField time = new TextField("Due Time");
        private final TextField possPoints = new TextField("Possible Points");

        public Stu_AssignmentDetailsFormLayout(){
                Stream.of(courseTitle, assignmentTitle, dueDate, time, possPoints)
                        .forEach(field -> {
                                field.setReadOnly(true);
                                add(field);
                        });
                setResponsiveSteps(new ResponsiveStep("0", 3));
                setColspan(courseTitle, 3);
                setColspan(assignmentTitle, 3);
        }

        public void setAssignment(Stu_Assignments assignment){
                courseTitle.setValue(assignment.getCourseTitle());
                assignmentTitle.setValue(assignment.getAssignmentTitle());
                dueDate.setValue(assignment.getDueDate());
                time.setValue(assignment.getTime());
                possPoints.setValue(assignment.getPossiblePoints());

        }

}

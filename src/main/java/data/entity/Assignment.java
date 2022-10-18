package data;

import java.util.Date;

public class Assignment {
    private String assignmentName;
    private String assignmentDetails;
    private String dueDate;

    public Assignment(String assignmentName, String assignmentDetails, String dueDate){
        this.assignmentName = assignmentName;
        this.assignmentDetails = assignmentDetails;
        this.dueDate = dueDate;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentDetails() {
        return assignmentDetails;
    }

    public void setAssignmentDetails(String assignmentDetails) {
        this.assignmentDetails = assignmentDetails;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

}

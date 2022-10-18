package data;

public class Stu_Assignments {

    public Stu_Assignments(String courseTitle, String assignmentTitle, String dueDate, String time,
                           String possiblePoints){
        this.courseTitle = courseTitle;
        this.assignmentTitle = assignmentTitle;
        this.dueDate = dueDate;
        this.time = time;
        this.possiblePoints = possiblePoints;

    }
    protected String courseTitle = "";
    protected String assignmentTitle = "";
    protected String dueDate = "";

    protected String time = "";

    protected String possiblePoints = "";


    public String getPossiblePoints(){
        return possiblePoints;
    }

    public void setPossiblePoints(String possiblePoints){
        this.possiblePoints = possiblePoints;
    }
    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

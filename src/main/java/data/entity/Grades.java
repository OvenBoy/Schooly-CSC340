package data.entity;

public class Grades {

    private String assignmentName;

    private String studentName;

    private String grade;

    public Grades(String assignmentName, String studentName, String grade) {
        this.assignmentName = assignmentName;
        this.studentName = studentName;
        this.grade = grade;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}

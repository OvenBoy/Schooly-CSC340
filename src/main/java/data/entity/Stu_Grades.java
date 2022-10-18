package data;

public class Stu_Grades {

    public Stu_Grades(String courseTitle, String assignmentTitle, String fractionGrade, double percentGrade){
        this.courseTitle = courseTitle;
        this.assignmentTitle = assignmentTitle;
        this.fractionGrade = fractionGrade;
        this.percentGrade = percentGrade;
    }
    protected String courseTitle = "";
    protected String assignmentTitle = "";
    protected String fractionGrade = "";
    protected double percentGrade = 0;

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

    public String getFractionGrade() {
        return fractionGrade;
    }

    public void setFractionGrade(String fractionGrade) {
        this.fractionGrade = fractionGrade;
    }

    public double getPercentGrade() {
        return percentGrade;
    }

    public void setPercentGrade(double percentGrade) {
        this.percentGrade = percentGrade;
    }
}

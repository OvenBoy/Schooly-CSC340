package data.entity;

public class Course {
    private String courseID;
    private String courseName;
    private String StudentCount;
    private String courseYear;

    public Course(String courseID, String courseName, String StudentCount){
        this.courseID = courseID;
        this.courseName = courseName;
        this.StudentCount = StudentCount;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setStudentCount(String StudentCount) {
        this.StudentCount = StudentCount;
    }

    public String getStudentCount() {
        return StudentCount;
    }
}

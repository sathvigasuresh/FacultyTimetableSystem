
import java.io.Serializable;

public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String subjectId;
    private String subjectName;
    private String department;
    private int weeklyHours;

    public Subject(String subjectId, String subjectName,
                   String department, int weeklyHours) {

        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.department = department;
        this.weeklyHours = weeklyHours;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getDepartment() {
        return department;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }

    public void displayDetails() {
        System.out.println("Subject ID: " + subjectId);
        System.out.println("Subject Name: " + subjectName);
        System.out.println("Department: " + department);
        System.out.println("Weekly Hours: " + weeklyHours);
    }
}

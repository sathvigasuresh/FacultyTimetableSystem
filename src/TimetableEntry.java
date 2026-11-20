
import java.io.Serializable;

public class TimetableEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    private String day;
    private int period;
    private String subjectId;
    private String facultyId;
    private String roomId;

    public TimetableEntry(String day, int period,
                          String subjectId,
                          String facultyId,
                          String roomId) {
        this.day = day;
        this.period = period;
        this.subjectId = subjectId;
        this.facultyId = facultyId;
        this.roomId = roomId;
    }

    public String getDay() {
        return day;
    }

    public int getPeriod() {
        return period;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void displayDetails() {
        System.out.println("Day: " + day);
        System.out.println("Period: " + period);
        System.out.println("Subject ID: " + subjectId);
        System.out.println("Faculty ID: " + facultyId);
        System.out.println("Room ID: " + roomId);
    }
}

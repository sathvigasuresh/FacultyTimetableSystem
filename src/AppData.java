
import java.io.Serializable;
import java.util.ArrayList;

public class AppData implements Serializable {

    private static final long serialVersionUID = 1L;

    public ArrayList<Faculty> facultyList;
    public ArrayList<Subject> subjectList;
    public ArrayList<Classroom> classroomList;
    public ArrayList<TimetableEntry> timetableList;

    public AppData() {
        facultyList = new ArrayList<>();
        subjectList = new ArrayList<>();
        classroomList = new ArrayList<>();
        timetableList = new ArrayList<>();
    }
}

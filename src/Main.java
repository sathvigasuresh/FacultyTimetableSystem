
import java.util.ArrayList;

public class Main {

    static AppData data = DataStorage.load();

    static ArrayList<Faculty> facultyList = data.facultyList;
    static ArrayList<Subject> subjectList = data.subjectList;
    static ArrayList<Classroom> classroomList = data.classroomList;
    static ArrayList<TimetableEntry> timetableList = data.timetableList;

    public static void saveData() {
        DataStorage.save(data);
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new TimetableGUI().setVisible(true);
        });
    }
}

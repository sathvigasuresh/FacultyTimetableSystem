
import java.util.ArrayList;

public class TimetableReportTask implements Runnable {

    private ArrayList<TimetableEntry> timetableList;

    public TimetableReportTask(ArrayList<TimetableEntry> timetableList) {
        this.timetableList = new ArrayList<>(timetableList);
    }

    @Override
    public void run() {

        System.out.println("\nGenerating timetable report...");
        System.out.println("Report running in background thread.");

        int totalEntries = timetableList.size();

        int monday = 0;
        int tuesday = 0;
        int wednesday = 0;
        int thursday = 0;
        int friday = 0;

        for (TimetableEntry entry : timetableList) {

            String day = entry.getDay();

            if (day.equalsIgnoreCase("Monday")) {
                monday++;
            } else if (day.equalsIgnoreCase("Tuesday")) {
                tuesday++;
            } else if (day.equalsIgnoreCase("Wednesday")) {
                wednesday++;
            } else if (day.equalsIgnoreCase("Thursday")) {
                thursday++;
            } else if (day.equalsIgnoreCase("Friday")) {
                friday++;
            }
        }

        System.out.println("\n===== TIMETABLE SUMMARY =====");
        System.out.println("Total scheduled classes: " + totalEntries);
        System.out.println("Monday: " + monday);
        System.out.println("Tuesday: " + tuesday);
        System.out.println("Wednesday: " + wednesday);
        System.out.println("Thursday: " + thursday);
        System.out.println("Friday: " + friday);

        System.out.println("=============================");
        System.out.println("Report generation completed.");
    }
}

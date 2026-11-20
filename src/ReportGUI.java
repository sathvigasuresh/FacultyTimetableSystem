
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReportGUI extends JFrame {

    private final Color navy = new Color(20, 35, 65);
    private final Color teal = new Color(35, 180, 165);

    private JTextArea reportArea;

    public ReportGUI() {

        setTitle("Workload Reports");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(15, 15));
        main.setBackground(new Color(242, 246, 252));
        main.setBorder(new EmptyBorder(25, 30, 25, 30));

        JLabel heading = new JLabel("WORKLOAD REPORTS");
        heading.setFont(new Font("Arial", Font.BOLD, 27));
        heading.setForeground(navy);

        JLabel subtitle = new JLabel(
            "View faculty and department workload information"
        );
        subtitle.setForeground(Color.GRAY);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(heading);
        header.add(Box.createVerticalStrut(8));
        header.add(subtitle);

        reportArea = new JTextArea();
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        reportArea.setEditable(false);
        reportArea.setMargin(new Insets(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setBorder(new LineBorder(new Color(220, 228, 240)));

        JButton facultyButton = new JButton("Faculty-wise Report");
        JButton departmentButton = new JButton("Department-wise Report");
        JButton summaryButton = new JButton("Timetable Summary");

        styleButton(facultyButton, teal);
        styleButton(departmentButton, navy);
        styleButton(summaryButton, new Color(135, 105, 195));

        facultyButton.addActionListener(e -> facultyReport());
        departmentButton.addActionListener(e -> departmentReport());
        summaryButton.addActionListener(e -> timetableSummary());

        JPanel buttons = new JPanel(new GridLayout(1, 3, 12, 12));
        buttons.setOpaque(false);
        buttons.add(facultyButton);
        buttons.add(departmentButton);
        buttons.add(summaryButton);

        main.add(header, BorderLayout.NORTH);
        main.add(scrollPane, BorderLayout.CENTER);
        main.add(buttons, BorderLayout.SOUTH);

        add(main);

        reportArea.setText(
            "Select a report option above to generate a report."
        );
    }

    private void styleButton(JButton button, Color color) {

        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(13, 8, 13, 8));
        button.setOpaque(true);
    }

    // FACULTY-WISE WORKLOAD REPORT
    private void facultyReport() {

        StringBuilder report = new StringBuilder();

        report.append("FACULTY-WISE WORKLOAD REPORT\n");
        report.append("========================================\n\n");

        if (Main.facultyList == null || Main.facultyList.isEmpty()) {
            report.append("No faculty records available.");
        } else {

            for (Faculty faculty : Main.facultyList) {

                if (faculty == null) continue;

                int workload = 0;

                for (TimetableEntry entry : Main.timetableList) {

                    if (entry != null
                            && entry.getFacultyId() != null
                            && faculty.getFacultyId() != null
                            && entry.getFacultyId().equalsIgnoreCase(
                                faculty.getFacultyId())) {

                        workload++;
                    }
                }

                report.append("Faculty ID: ")
                    .append(faculty.getFacultyId())
                    .append("\nName: ")
                    .append(faculty.getName())
                    .append("\nDepartment: ")
                    .append(faculty.getDepartment())
                    .append("\nDesignation: ")
                    .append(faculty.getDesignation())
                    .append("\nAllocated Periods: ")
                    .append(workload)
                    .append("\n\n");
            }
        }

        reportArea.setText(report.toString());
        reportArea.setCaretPosition(0);
    }

    // DEPARTMENT-WISE WORKLOAD REPORT
    private void departmentReport() {

        Map<String, Integer> departmentWorkload =
            new LinkedHashMap<>();

        if (Main.facultyList != null) {

            for (Faculty faculty : Main.facultyList) {

                if (faculty != null) {

                    String department = faculty.getDepartment();

                    if (department != null && !department.isBlank()) {
                        departmentWorkload.putIfAbsent(department, 0);
                    }
                }
            }
        }

        if (Main.timetableList != null) {

            for (TimetableEntry entry : Main.timetableList) {

                if (entry == null || entry.getFacultyId() == null) {
                    continue;
                }

                for (Faculty faculty : Main.facultyList) {

                    if (faculty != null
                            && faculty.getFacultyId() != null
                            && faculty.getFacultyId().equalsIgnoreCase(
                                entry.getFacultyId())) {

                        String department = faculty.getDepartment();

                        if (department != null
                                && !department.isBlank()) {

                            departmentWorkload.put(
                                department,
                                departmentWorkload.getOrDefault(
                                    department, 0
                                ) + 1
                            );
                        }

                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();

        report.append("DEPARTMENT-WISE WORKLOAD REPORT\n");
        report.append("========================================\n\n");

        if (departmentWorkload.isEmpty()) {
            report.append("No department records available.");
        } else {

            for (Map.Entry<String, Integer> item :
                    departmentWorkload.entrySet()) {

                report.append("Department: ")
                    .append(item.getKey())
                    .append("\nTotal Allocated Periods: ")
                    .append(item.getValue())
                    .append("\n\n");
            }
        }

        reportArea.setText(report.toString());
        reportArea.setCaretPosition(0);
    }

    // TIMETABLE SUMMARY
    private void timetableSummary() {

        StringBuilder report = new StringBuilder();

        report.append("TIMETABLE SUMMARY\n");
        report.append("========================================\n\n");

        report.append("Total Faculty: ")
            .append(Main.facultyList.size())
            .append("\n");

        report.append("Total Subjects: ")
            .append(Main.subjectList.size())
            .append("\n");

        report.append("Total Classrooms: ")
            .append(Main.classroomList.size())
            .append("\n");

        report.append("Total Timetable Entries: ")
            .append(Main.timetableList.size())
            .append("\n");

        report.append("\nEach timetable entry is counted as one period.");

        reportArea.setText(report.toString());
        reportArea.setCaretPosition(0);
    }
}

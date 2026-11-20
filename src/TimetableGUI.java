
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class TimetableGUI extends JFrame {

    private final Color navy = new Color(20, 35, 65);
    private final Color blue = new Color(42, 105, 180);
    private final Color teal = new Color(35, 180, 165);
    private final Color orange = new Color(225, 150, 65);
    private final Color purple = new Color(135, 105, 195);
    private final Color background = new Color(242, 246, 252);

    private JLabel facultyCount;
    private JLabel subjectCount;
    private JLabel classroomCount;
    private JLabel timetableCount;

    public TimetableGUI() {

        setTitle("Faculty Workload and Timetable Management System");
        setSize(1100, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(background);

        // SIDEBAR
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(230, 0));
        sidebar.setBackground(navy);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(30, 18, 25, 18));

        JLabel logo = new JLabel("CAMPUS");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel subtitle = new JLabel("MANAGEMENT PORTAL");
        subtitle.setForeground(new Color(190, 205, 225));
        subtitle.setFont(new Font("Arial", Font.PLAIN, 11));

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(subtitle);
        sidebar.add(Box.createVerticalStrut(45));

        addMenu(sidebar, "Dashboard", this::refreshDashboard);
        addMenu(sidebar, "Faculty", this::openFaculty);
        addMenu(sidebar, "Subjects", this::openSubjects);
        addMenu(sidebar, "Classrooms", this::openClassrooms);
        addMenu(sidebar, "Timetable", this::openTimetable);
        addMenu(sidebar, "Reports", this::openReports);

        sidebar.add(Box.createVerticalGlue());

        JLabel footer = new JLabel("Faculty Timetable System");
        footer.setForeground(Color.LIGHT_GRAY);
        footer.setFont(new Font("Arial", Font.PLAIN, 11));
        sidebar.add(footer);

        // MAIN CONTENT
        JPanel content = new JPanel(new BorderLayout(20, 25));
        content.setBackground(background);
        content.setBorder(new EmptyBorder(35, 35, 35, 35));

        JLabel heading = new JLabel("Welcome to your Dashboard");
        heading.setFont(new Font("Arial", Font.BOLD, 29));
        heading.setForeground(navy);

        JLabel description = new JLabel(
            "Manage your academic resources and weekly schedules."
        );
        description.setFont(new Font("Arial", Font.PLAIN, 15));
        description.setForeground(Color.GRAY);

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        header.add(heading);
        header.add(Box.createVerticalStrut(8));
        header.add(description);

        // STATISTICS
        JPanel stats = new JPanel(new GridLayout(1, 4, 15, 15));
        stats.setOpaque(false);

        facultyCount = new JLabel();
        subjectCount = new JLabel();
        classroomCount = new JLabel();
        timetableCount = new JLabel();

        stats.add(createStat(
            "FACULTY", facultyCount, blue
        ));

        stats.add(createStat(
            "SUBJECTS", subjectCount, teal
        ));

        stats.add(createStat(
            "CLASSROOMS", classroomCount, orange
        ));

        stats.add(createStat(
            "SCHEDULES", timetableCount, purple
        ));

        // MODULE CARDS
        JPanel cards = new JPanel(new GridLayout(2, 2, 20, 20));
        cards.setOpaque(false);

        cards.add(createCard(
            "Faculty Management",
            "Register and view faculty information",
            blue,
            this::openFaculty
        ));

        cards.add(createCard(
            "Subject Management",
            "Manage subjects and weekly hours",
            teal,
            this::openSubjects
        ));

        cards.add(createCard(
            "Classroom Management",
            "Register and manage classrooms",
            orange,
            this::openClassrooms
        ));

        cards.add(createCard(
            "Timetable Management",
            "Allocate faculty, subjects and rooms",
            purple,
            this::openTimetable
        ));

        JPanel center = new JPanel(new BorderLayout(20, 25));
        center.setOpaque(false);
        center.add(stats, BorderLayout.NORTH);
        center.add(cards, BorderLayout.CENTER);

        content.add(header, BorderLayout.NORTH);
        content.add(center, BorderLayout.CENTER);

        main.add(sidebar, BorderLayout.WEST);
        main.add(content, BorderLayout.CENTER);

        add(main);

        refreshDashboard();
    }

    // SIDEBAR BUTTONS
    private void addMenu(
        JPanel sidebar, String name, Runnable action
    ) {

        JButton button = new JButton(name);

        button.setMaximumSize(new Dimension(195, 48));
        button.setPreferredSize(new Dimension(195, 48));
        button.setBackground(navy);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(12, 15, 12, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> action.run());

        sidebar.add(button);
        sidebar.add(Box.createVerticalStrut(8));
    }

    // STATISTICS CARDS
    private JPanel createStat(
        String title, JLabel value, Color color
    ) {

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder(new CompoundBorder(
            new LineBorder(new Color(220, 228, 240)),
            new EmptyBorder(18, 15, 18, 15)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.GRAY);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));

        value.setForeground(color);
        value.setFont(new Font("Arial", Font.BOLD, 30));

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(12));
        panel.add(value);

        return panel;
    }

    // DASHBOARD MODULE CARDS
    private JPanel createCard(
        String title,
        String description,
        Color color,
        Runnable action
    ) {

        JPanel panel = new JPanel(new BorderLayout(10, 12));
        panel.setBackground(Color.WHITE);

        panel.setBorder(new CompoundBorder(
            new LineBorder(new Color(220, 228, 240)),
            new EmptyBorder(22, 20, 22, 20)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 17));
        titleLabel.setForeground(navy);

        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        descriptionLabel.setForeground(Color.GRAY);

        JButton openButton = new JButton("Open Module  →");
        openButton.setBackground(color);
        openButton.setForeground(Color.WHITE);
        openButton.setFont(new Font("Arial", Font.BOLD, 13));
        openButton.setFocusPainted(false);
        openButton.setBorder(new EmptyBorder(11, 12, 11, 12));
        openButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        openButton.addActionListener(e -> action.run());

        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));

        text.add(titleLabel);
        text.add(Box.createVerticalStrut(9));
        text.add(descriptionLabel);

        panel.add(text, BorderLayout.CENTER);
        panel.add(openButton, BorderLayout.SOUTH);

        return panel;
    }

    // OPEN FACULTY MANAGEMENT
    private void openFaculty() {
        new FacultyManagementGUI().setVisible(true);
    }

    // OPEN SUBJECT MANAGEMENT
    private void openSubjects() {
        new SubjectManagementGUI().setVisible(true);
    }

    // OPEN CLASSROOM MANAGEMENT
    private void openClassrooms() {
        new ClassroomManagementGUI().setVisible(true);
    }

    // OPEN TIMETABLE MANAGEMENT
    private void openTimetable() {
        new TimetableManagementGUI().setVisible(true);
    }

    // OPEN REPORTS
    private void openReports() {
        new ReportGUI().setVisible(true);
    }

    // REFRESH DASHBOARD COUNTS
    private void refreshDashboard() {

        facultyCount.setText(
            String.valueOf(Main.facultyList.size())
        );

        subjectCount.setText(
            String.valueOf(Main.subjectList.size())
        );

        classroomCount.setText(
            String.valueOf(Main.classroomList.size())
        );

        timetableCount.setText(
            String.valueOf(Main.timetableList.size())
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TimetableGUI().setVisible(true);
        });
    }
}

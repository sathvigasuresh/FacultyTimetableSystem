
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class SubjectManagementGUI extends JFrame {

    private final Color navy = new Color(20, 35, 65);
    private final Color teal = new Color(35, 180, 165);
    private final Color background = new Color(242, 246, 252);

    public SubjectManagementGUI() {

        setTitle("Subject Management");
        setSize(650, 430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(20, 20));
        main.setBackground(background);
        main.setBorder(new EmptyBorder(25, 30, 25, 30));

        JLabel heading = new JLabel("SUBJECT MANAGEMENT");
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        heading.setForeground(navy);

        JLabel subtitle = new JLabel(
            "Add and manage department subjects"
        );
        subtitle.setForeground(Color.GRAY);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        header.add(heading);
        header.add(Box.createVerticalStrut(8));
        header.add(subtitle);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 15, 15));
        buttonPanel.setOpaque(false);

        JButton addButton = new JButton("＋  Add New Subject");
        JButton displayButton = new JButton("▤  Display All Subjects");

        styleButton(addButton, teal);
        styleButton(displayButton, navy);

        addButton.addActionListener(e -> addSubject());
        displayButton.addActionListener(e -> displaySubjects());

        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);

        main.add(header, BorderLayout.NORTH);
        main.add(buttonPanel, BorderLayout.CENTER);

        add(main);
    }

    private void styleButton(JButton button, Color color) {

        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(18, 20, 18, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void addSubject() {

        String id = JOptionPane.showInputDialog(
            this, "Enter Subject ID:"
        );

        if (id == null || id.trim().isEmpty()) return;

        id = id.trim();

        for (Subject s : Main.subjectList) {

            if (s.getSubjectId().equalsIgnoreCase(id)) {
                JOptionPane.showMessageDialog(
                    this, "Subject ID already exists!"
                );
                return;
            }
        }

        String name = JOptionPane.showInputDialog(
            this, "Enter Subject Name:"
        );

        if (name == null || name.trim().isEmpty()) return;

        String department = JOptionPane.showInputDialog(
            this, "Enter Department:"
        );

        if (department == null || department.trim().isEmpty()) return;

        String hoursText = JOptionPane.showInputDialog(
            this, "Enter Weekly Hours:"
        );

        if (hoursText == null) return;

        int hours;

        try {
            hours = Integer.parseInt(hoursText.trim());

            if (hours <= 0) {
                JOptionPane.showMessageDialog(
                    this, "Weekly hours must be greater than zero."
                );
                return;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this, "Please enter a valid number."
            );
            return;
        }

        Subject subject = new Subject(
            id, name.trim(), department.trim(), hours
        );

        Main.subjectList.add(subject);

        JOptionPane.showMessageDialog(
            this,
            "Subject added successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void displaySubjects() {

        if (Main.subjectList.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "No subjects registered yet."
            );
            return;
        }

        String[] columns = {
            "Subject ID",
            "Subject Name",
            "Department",
            "Weekly Hours"
        };

        String[][] rows = new String[Main.subjectList.size()][4];

        for (int i = 0; i < Main.subjectList.size(); i++) {

            Subject s = Main.subjectList.get(i);

            rows[i][0] = s.getSubjectId();
            rows[i][1] = s.getSubjectName();
            rows[i][2] = s.getDepartment();
            rows[i][3] = String.valueOf(s.getWeeklyHours());
        }

        JTable table = new JTable(rows, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));

        table.getTableHeader().setFont(
            new Font("Arial", Font.BOLD, 13)
        );

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(550, 250));

        JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Registered Subjects",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}

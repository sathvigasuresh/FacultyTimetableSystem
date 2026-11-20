
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class TimetableManagementGUI extends JFrame {

    private final Color navy = new Color(20, 35, 65);
    private final Color teal = new Color(35, 180, 165);
    private final Color background = new Color(242, 246, 252);

    private JComboBox<String> dayBox;
    private JComboBox<Integer> periodBox;
    private JComboBox<String> subjectBox;
    private JComboBox<String> facultyBox;
    private JComboBox<String> roomBox;

    public TimetableManagementGUI() {

        setTitle("Timetable Management");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(20, 20));
        main.setBackground(background);
        main.setBorder(new EmptyBorder(25, 30, 25, 30));

        JLabel heading = new JLabel("TIMETABLE MANAGEMENT");
        heading.setFont(new Font("Arial", Font.BOLD, 27));
        heading.setForeground(navy);

        JLabel subtitle = new JLabel(
            "Allocate faculty, subjects, classrooms and time slots"
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(Color.GRAY);

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(heading);
        header.add(Box.createVerticalStrut(8));
        header.add(subtitle);

        JPanel form = new JPanel(new GridLayout(5, 2, 15, 15));
        form.setBackground(Color.WHITE);
        form.setBorder(new CompoundBorder(
            new LineBorder(new Color(220, 228, 240)),
            new EmptyBorder(25, 25, 25, 25)
        ));

        dayBox = new JComboBox<>(new String[]{
            "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday"
        });

        periodBox = new JComboBox<>(
            new Integer[]{1, 2, 3, 4, 5, 6}
        );

        subjectBox = new JComboBox<>();
        facultyBox = new JComboBox<>();
        roomBox = new JComboBox<>();

        addField(form, "Day", dayBox);
        addField(form, "Period", periodBox);
        addField(form, "Subject", subjectBox);
        addField(form, "Faculty", facultyBox);
        addField(form, "Classroom", roomBox);

        refreshDropdowns();

        JButton addButton = new JButton("＋  Add Timetable Entry");
        JButton displayButton = new JButton("▤  View Timetable");

        styleButton(addButton, teal);
        styleButton(displayButton, navy);

        addButton.addActionListener(e -> addTimetableEntry());
        displayButton.addActionListener(e -> displayTimetable());

        JPanel buttons = new JPanel(new GridLayout(1, 2, 15, 15));
        buttons.setOpaque(false);
        buttons.add(addButton);
        buttons.add(displayButton);

        JPanel center = new JPanel(new BorderLayout(15, 20));
        center.setOpaque(false);
        center.add(form, BorderLayout.CENTER);
        center.add(buttons, BorderLayout.SOUTH);

        main.add(header, BorderLayout.NORTH);
        main.add(center, BorderLayout.CENTER);

        add(main);
    }

    private void addField(
        JPanel panel, String label, JComboBox<?> combo
    ) {
        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fieldLabel.setForeground(navy);

        combo.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(fieldLabel);
        panel.add(combo);
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(15, 12, 15, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
    }

    private void refreshDropdowns() {

        subjectBox.removeAllItems();
        facultyBox.removeAllItems();
        roomBox.removeAllItems();

        for (Subject s : Main.subjectList) {
            if (s != null && s.getSubjectId() != null) {
                subjectBox.addItem(
                    s.getSubjectId() + " - " + s.getSubjectName()
                );
            }
        }

        for (Faculty f : Main.facultyList) {
            if (f != null && f.getFacultyId() != null) {
                facultyBox.addItem(
                    f.getFacultyId() + " - " + f.getName()
                );
            }
        }

        for (Classroom c : Main.classroomList) {
            if (c != null && c.getRoomId() != null) {
                roomBox.addItem(
                    c.getRoomId() + " - " + c.getRoomType()
                );
            }
        }
    }

    private void addTimetableEntry() {

        try {

            refreshDropdowns();

            if (subjectBox.getItemCount() == 0
                    || facultyBox.getItemCount() == 0
                    || roomBox.getItemCount() == 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please add at least one valid subject, faculty "
                    + "member and classroom first."
                );
                return;
            }

            String day = (String) dayBox.getSelectedItem();
            Integer selectedPeriod =
                (Integer) periodBox.getSelectedItem();

            String subject = (String) subjectBox.getSelectedItem();
            String faculty = (String) facultyBox.getSelectedItem();
            String room = (String) roomBox.getSelectedItem();

            if (day == null || selectedPeriod == null
                    || subject == null || faculty == null
                    || room == null) {

                JOptionPane.showMessageDialog(
                    this, "Please select all timetable details."
                );
                return;
            }

            int period = selectedPeriod;

            String subjectId = subject.split(" - ", 2)[0];
            String facultyId = faculty.split(" - ", 2)[0];
            String roomId = room.split(" - ", 2)[0];

            // Check scheduling conflicts
            for (TimetableEntry entry : Main.timetableList) {

                if (entry == null || entry.getDay() == null) {
                    continue;
                }

                if (entry.getDay().equalsIgnoreCase(day)
                        && entry.getPeriod() == period) {

                    if (entry.getFacultyId() != null
                            && entry.getFacultyId().equalsIgnoreCase(facultyId)) {

                        JOptionPane.showMessageDialog(
                            this,
                            "Conflict! This faculty member is already "
                            + "assigned during this period."
                        );
                        return;
                    }

                    if (entry.getRoomId() != null
                            && entry.getRoomId().equalsIgnoreCase(roomId)) {

                        JOptionPane.showMessageDialog(
                            this,
                            "Conflict! This classroom is already "
                            + "booked during this period."
                        );
                        return;
                    }
                }
            }

            TimetableEntry newEntry = new TimetableEntry(
                day, period, subjectId, facultyId, roomId
            );

            Main.timetableList.add(newEntry);

            // Save timetable permanently
            Main.saveData();

            JOptionPane.showMessageDialog(
                this,
                "Timetable entry added and saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                this,
                "Unable to add timetable entry: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
    }

    private void displayTimetable() {

        if (Main.timetableList == null
                || Main.timetableList.isEmpty()) {

            JOptionPane.showMessageDialog(
                this, "No timetable entries found."
            );
            return;
        }

        String[] columns = {
            "Day", "Period", "Subject ID",
            "Faculty ID", "Room ID"
        };

        String[][] rows =
            new String[Main.timetableList.size()][5];

        for (int i = 0; i < Main.timetableList.size(); i++) {

            TimetableEntry t = Main.timetableList.get(i);

            if (t == null) {
                for (int j = 0; j < 5; j++) {
                    rows[i][j] = "";
                }
                continue;
            }

            rows[i][0] = String.valueOf(t.getDay());
            rows[i][1] = String.valueOf(t.getPeriod());
            rows[i][2] = String.valueOf(t.getSubjectId());
            rows[i][3] = String.valueOf(t.getFacultyId());
            rows[i][4] = String.valueOf(t.getRoomId());
        }

        JTable table = new JTable(rows, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));

        table.getTableHeader().setFont(
            new Font("Arial", Font.BOLD, 13)
        );

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 300));

        JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Weekly Timetable",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}

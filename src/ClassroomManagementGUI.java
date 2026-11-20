
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class ClassroomManagementGUI extends JFrame {

    private final Color navy = new Color(20, 35, 65);
    private final Color teal = new Color(35, 180, 165);
    private final Color background = new Color(242, 246, 252);

    public ClassroomManagementGUI() {

        setTitle("Classroom Management");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(20, 20));
        main.setBackground(background);
        main.setBorder(new EmptyBorder(30, 35, 30, 35));

        JLabel heading = new JLabel("CLASSROOM MANAGEMENT");
        heading.setFont(new Font("Arial", Font.BOLD, 27));
        heading.setForeground(navy);

        JLabel subtitle = new JLabel(
            "Register, organize and manage classrooms"
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setForeground(Color.GRAY);

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        header.add(heading);
        header.add(Box.createVerticalStrut(8));
        header.add(subtitle);

        JPanel cards = new JPanel(new GridLayout(1, 2, 20, 20));
        cards.setOpaque(false);

        JButton addButton = new JButton("＋  ADD CLASSROOM");
        JButton displayButton = new JButton("▤  VIEW CLASSROOMS");

        styleButton(addButton, teal);
        styleButton(displayButton, navy);

        addButton.addActionListener(e -> addClassroom());
        displayButton.addActionListener(e -> displayClassrooms());

        cards.add(addButton);
        cards.add(displayButton);

        JLabel footer = new JLabel(
            "Faculty Workload and Timetable Management System"
        );
        footer.setForeground(Color.GRAY);
        footer.setFont(new Font("Arial", Font.PLAIN, 12));

        main.add(header, BorderLayout.NORTH);
        main.add(cards, BorderLayout.CENTER);
        main.add(footer, BorderLayout.SOUTH);

        add(main);
    }

    private void styleButton(JButton button, Color color) {

        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(20, 15, 20, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
    }

    private void addClassroom() {

        try {

            String id = JOptionPane.showInputDialog(
                this, "Enter Room ID:"
            );

            if (id == null) return;

            id = id.trim();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Room ID cannot be empty."
                );
                return;
            }

            if (Main.classroomList == null) {
                JOptionPane.showMessageDialog(
                    this, "Classroom list is not initialized."
                );
                return;
            }

            for (Classroom c : Main.classroomList) {

                if (c != null && c.getRoomId() != null
                        && c.getRoomId().equalsIgnoreCase(id)) {

                    JOptionPane.showMessageDialog(
                        this, "Room ID already exists!"
                    );
                    return;
                }
            }

            String type = JOptionPane.showInputDialog(
                this, "Enter Room Type (Classroom/Lab):"
            );

            if (type == null || type.trim().isEmpty()) return;

            String building = JOptionPane.showInputDialog(
                this, "Enter Building Name:"
            );

            if (building == null || building.trim().isEmpty()) return;

            String capacityText = JOptionPane.showInputDialog(
                this, "Enter Classroom Capacity:"
            );

            if (capacityText == null) return;

            int capacity = Integer.parseInt(capacityText.trim());

            if (capacity <= 0) {
                JOptionPane.showMessageDialog(
                    this, "Capacity must be greater than zero."
                );
                return;
            }

            Classroom classroom = new Classroom(
                id, type.trim(), building.trim(), capacity
            );

            Main.classroomList.add(classroom);

            // Save classroom information permanently
            Main.saveData();

            JOptionPane.showMessageDialog(
                this,
                "Classroom added and saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this, "Enter a valid capacity number."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Unable to add classroom: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void displayClassrooms() {

        if (Main.classroomList == null
                || Main.classroomList.isEmpty()) {

            JOptionPane.showMessageDialog(
                this, "No classrooms registered yet."
            );
            return;
        }

        String[] columns = {
            "Room ID", "Room Type", "Building", "Capacity"
        };

        String[][] rows =
            new String[Main.classroomList.size()][4];

        for (int i = 0; i < Main.classroomList.size(); i++) {

            Classroom c = Main.classroomList.get(i);

            if (c == null) {
                rows[i][0] = "";
                rows[i][1] = "";
                rows[i][2] = "";
                rows[i][3] = "";
                continue;
            }

            rows[i][0] = String.valueOf(c.getRoomId());
            rows[i][1] = String.valueOf(c.getRoomType());
            rows[i][2] = String.valueOf(c.getBuilding());
            rows[i][3] = String.valueOf(c.getCapacity());
        }

        JTable table = new JTable(rows, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));

        table.getTableHeader().setFont(
            new Font("Arial", Font.BOLD, 13)
        );

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 280));

        JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Registered Classrooms",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}

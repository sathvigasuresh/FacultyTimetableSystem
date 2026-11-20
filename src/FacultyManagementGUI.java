
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class FacultyManagementGUI extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField departmentField;
    private JTextField designationField;

    private final Color navy = new Color(20, 35, 65);
    private final Color teal = new Color(35, 180, 165);

    public FacultyManagementGUI() {

        setTitle("Faculty Management");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(20, 20));
        main.setBackground(new Color(242, 246, 252));
        main.setBorder(new EmptyBorder(25, 30, 25, 30));

        JLabel heading = new JLabel("FACULTY MANAGEMENT");
        heading.setFont(new Font("Arial", Font.BOLD, 27));
        heading.setForeground(navy);

        JLabel subtitle = new JLabel(
            "Register and manage faculty information"
        );

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(heading);
        header.add(Box.createVerticalStrut(8));
        header.add(subtitle);

        JPanel form = new JPanel(new GridLayout(4, 2, 15, 20));
        form.setBackground(Color.WHITE);
        form.setBorder(new CompoundBorder(
            new LineBorder(new Color(220, 228, 240)),
            new EmptyBorder(25, 25, 25, 25)
        ));

        idField = new JTextField();
        nameField = new JTextField();
        departmentField = new JTextField();
        designationField = new JTextField();

        addField(form, "Faculty ID", idField);
        addField(form, "Faculty Name", nameField);
        addField(form, "Department", departmentField);
        addField(form, "Designation", designationField);

        JButton addButton = new JButton("Add Faculty");
        JButton displayButton = new JButton("Display Faculty");

        styleButton(addButton, teal);
        styleButton(displayButton, navy);

        addButton.addActionListener(e -> addFaculty());
        displayButton.addActionListener(e -> displayFaculty());

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
        JPanel panel, String label, JTextField field
    ) {
        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fieldLabel.setForeground(navy);

        field.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(fieldLabel);
        panel.add(field);
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(14, 12, 14, 12));
        button.setOpaque(true);
    }

    private void addFaculty() {

        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String department = departmentField.getText().trim();
            String designation = designationField.getText().trim();

            if (id.isEmpty() || name.isEmpty()
                    || department.isEmpty() || designation.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Please fill in all four fields."
                );
                return;
            }

            if (Main.facultyList == null) {
                JOptionPane.showMessageDialog(
                    this, "Faculty list is not initialized."
                );
                return;
            }

            for (Faculty f : Main.facultyList) {
                if (f != null && f.getFacultyId() != null
                        && f.getFacultyId().equalsIgnoreCase(id)) {
                    JOptionPane.showMessageDialog(
                        this, "Faculty ID already exists!"
                    );
                    return;
                }
            }

            Faculty faculty = new Faculty(
                id, name, department, designation
            );

            Main.facultyList.add(faculty);
            Main.saveData();

            JOptionPane.showMessageDialog(
                this, "Faculty added successfully!"
            );

            idField.setText("");
            nameField.setText("");
            departmentField.setText("");
            designationField.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this,
                "Unable to add faculty: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }
    }

    private void displayFaculty() {

        if (Main.facultyList == null || Main.facultyList.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "No faculty records found."
            );
            return;
        }

        String[] columns = {
            "Faculty ID", "Name", "Department", "Designation"
        };

        String[][] rows = new String[Main.facultyList.size()][4];

        for (int i = 0; i < Main.facultyList.size(); i++) {
            Faculty f = Main.facultyList.get(i);

            rows[i][0] = f == null ? "" : String.valueOf(f.getFacultyId());
            rows[i][1] = f == null ? "" : String.valueOf(f.getName());
            rows[i][2] = f == null ? "" : String.valueOf(f.getDepartment());
            rows[i][3] = f == null ? "" : String.valueOf(f.getDesignation());
        }

        JTable table = new JTable(rows, columns);
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(650, 250));

        JOptionPane.showMessageDialog(
            this, scrollPane, "Faculty Records",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}

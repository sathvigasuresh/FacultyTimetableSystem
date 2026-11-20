
public class Faculty extends Person {

    private static final long serialVersionUID = 1L;

    private String department;
    private String designation;

    public Faculty(String facultyId, String name,
                   String department, String designation) {

        super(facultyId, name);
        this.department = department;
        this.designation = designation;
    }

    public String getFacultyId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getDesignation() {
        return designation;
    }

    @Override
    public void displayDetails() {
        System.out.println("Faculty ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Designation: " + designation);
    }
}


import java.io.Serializable;

public class Classroom implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roomId;
    private String roomType;
    private String building;
    private int capacity;

    public Classroom(String roomId, String roomType,
                     String building, int capacity) {

        this.roomId = roomId;
        this.roomType = roomType;
        this.building = building;
        this.capacity = capacity;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getBuilding() {
        return building;
    }

    public int getCapacity() {
        return capacity;
    }

    public void displayDetails() {
        System.out.println("Room ID: " + roomId);
        System.out.println("Room Type: " + roomType);
        System.out.println("Building: " + building);
        System.out.println("Capacity: " + capacity);
    }
}


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class DataStorage {

    private static final String FILE_NAME = "system_data.dat";

    public static void save(AppData data) {

        try (ObjectOutputStream output =
                new ObjectOutputStream(
                    new FileOutputStream(FILE_NAME))) {

            output.writeObject(data);

            System.out.println("Data saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static AppData load() {

        try (ObjectInputStream input =
                new ObjectInputStream(
                    new FileInputStream(FILE_NAME))) {

            AppData data = (AppData) input.readObject();

            System.out.println("Previous data loaded successfully!");

            return data;

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("No previous data found. Starting fresh.");

            return new AppData();
        }
    }
}

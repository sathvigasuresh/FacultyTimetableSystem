
import java.util.Scanner;

public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    // Read an integer safely
    public static int readInt(String message) {

        while (true) {

            System.out.print(message);

            try {
                int number = Integer.parseInt(scanner.nextLine().trim());
                return number;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    // Read a non-empty string
    public static String readString(String message) {

        while (true) {

            System.out.print(message);

            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Input cannot be empty. Try again.");
        }
    }

    // Read a positive integer
    public static int readPositiveInt(String message) {

        while (true) {

            int number = readInt(message);

            if (number > 0) {
                return number;
            }

            System.out.println("Please enter a number greater than zero.");
        }
    }
}

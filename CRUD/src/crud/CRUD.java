package crud;
import java.sql.*;
import java.util.Scanner;

public class CRUD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OPTIONS(scanner);
        scanner.close();
    }

    public static void OPTIONS(Scanner scanner) {
        boolean loop = true;

        while (loop) {
            System.out.println("\nCRUD OPERATION");
            System.out.println("1   -   CREATE");
            System.out.println("2   -   READ");
            System.out.println("3   -   UPDATE");
            System.out.println("4   -   DELETE");
            System.out.println("5   -   EXIT");
            System.out.print("ENTER NUMBER HERE: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    CREATE(scanner);
                    break;
                case 2:
                    READ();
                    break;
                case 3:
                    UPDATE(scanner);
                    break;
                case 4:
                    DELETE(scanner);
                    break;
                case 5:
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        System.out.println("Thank you for using the system");
    }

    public static void CREATE(Scanner scanner) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3307/crud";
        String user = "root";
        String password = "Java@2024";

        // Sample data to insert
        System.out.print("NAME: ");
        String name = scanner.nextLine();
        System.out.print("AGE: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("YEAR & SECTION: ");
        String section = scanner.nextLine();

        // SQL INSERT query
        String sql = "INSERT INTO crud_table (name, age, section) VALUES (?, ?, ?)";

        // Establish a database connection
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, section);

            // Execute the insert statement
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new row was inserted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void READ() {
        // Database connection details
        String url = "jdbc:mysql://localhost:3307/crud";
        String user = "root";
        String password = "Java@2024";

        String query = "SELECT * FROM crud_table";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("ID          " + "NAME                       " + "AGE        " + "YEAR AND SECTION");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "           " + rs.getString("name") + "                     "
                        + rs.getInt("age") + "             " + rs.getString("section"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UPDATE(Scanner scanner) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3307/crud";
        String username = "root";
        String password = "Java@2024";

        // Gather input from the user
        System.out.print("ID of the student to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("NAME: ");
        String name = scanner.nextLine();
        System.out.print("AGE: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("YEAR & SECTION: ");
        String section = scanner.nextLine();

        String updateSQL = "UPDATE crud_table SET name = ?, age = ?, section = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, section);
            preparedStatement.setInt(4, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("No record found with the specified ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void DELETE(Scanner scanner) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3307/crud";
        String username = "root";
        String password = "Java@2024";

        System.out.print("ID of the student to delete: ");
        int id = scanner.nextInt();

        String deleteSQL = "DELETE FROM crud_table WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Record deleted successfully!");
            } else {
                System.out.println("No record found with the specified ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

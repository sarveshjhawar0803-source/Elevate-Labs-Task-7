import java.sql.*;
import java.util.Scanner;

public class EmployeeDBApp {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb"; // change DB name
    private static final String USER = "postgres"; // your PostgreSQL username
    private static final String PASSWORD = "98377389"; // your PostgreSQL password

    private Connection conn;
    private Scanner scanner;

    public EmployeeDBApp() {
        try {
            // Load PostgreSQL driver (optional in newer JDBC but safe to include)
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            scanner = new Scanner(System.in);
            System.out.println("✅ Connected to PostgreSQL database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add Employee
    private void addEmployee() {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();

            String sql = "INSERT INTO employee(name, salary) VALUES(?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDouble(2, salary);
            stmt.executeUpdate();
            System.out.println("✅ Employee added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Employees
    private void viewEmployees() {
        try {
            String sql = "SELECT * FROM employee";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("ID | Name | Salary");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Employee
    private void updateEmployee() {
        try {
            System.out.print("Enter employee ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter new salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();

            String sql = "UPDATE employee SET salary=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, salary);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee updated.");
            } else {
                System.out.println("⚠️ Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Employee
    private void deleteEmployee() {
        try {
            System.out.print("Enter employee ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            String sql = "DELETE FROM employee WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee deleted.");
            } else {
                System.out.println("⚠️ Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menu
    private void menu() {
        while (true) {
            System.out.println("\n--- Employee Database Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: viewEmployees(); break;
                case 3: updateEmployee(); break;
                case 4: deleteEmployee(); break;
                case 5:
                    System.out.println("👋 Goodbye!");
                    try { conn.close(); } catch (Exception e) {}
                    return;
                default: System.out.println("⚠️ Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        EmployeeDBApp app = new EmployeeDBApp();
        app.menu();
    }
}

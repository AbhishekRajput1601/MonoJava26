import java.sql.*;

public class JDBCDemo {

    static String url = "jdbc:mysql://localhost:3306/bank?useSSL=false";
    static String username = "root";
    static String password = "Abhishek@1137";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

//            readCustomers(connection);
//            insertCustomer(connection);
//            updateCustomer(connection);
//            deleteCustomer(connection);

            connection.close();

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }


    public static void insertCustomer(Connection connection) {
        try {
            String query = "INSERT INTO customers (customer_id, customer_name) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, 11);
            ps.setString(2, "Arjun");

            int rows = ps.executeUpdate();
            System.out.println("Inserted rows: " + rows);

        } catch (Exception e) {
            System.out.println("Insert Error: " + e.getMessage());
        }
    }


    public static void readCustomers(Connection connection) {
        try {
            String query = "SELECT * FROM customers";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(
                        "Customer ID: " + rs.getInt("customer_id") +
                                ", Name: " + rs.getString("customer_name")
                );
            }

        } catch (Exception e) {
            System.out.println("Read Error: " + e.getMessage());
        }
    }


    public static void updateCustomer(Connection connection) {
        try {
            String query = "UPDATE customers SET customer_name = ? WHERE customer_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, "Abhi");
            ps.setInt(2, 11);

            int rows = ps.executeUpdate();
            System.out.println("Updated rows: " + rows);

        } catch (Exception e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }


    public static void deleteCustomer(Connection connection) {
        try {
            String query = "DELETE FROM customers WHERE customer_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, 11);

            int rows = ps.executeUpdate();
            System.out.println("Deleted rows: " + rows);

        } catch (Exception e) {
            System.out.println("Delete Error: " + e.getMessage());
        }
    }
}
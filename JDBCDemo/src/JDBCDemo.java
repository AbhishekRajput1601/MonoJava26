import java.sql.*;

public class JDBCDemo {

    static String url = "jdbc:mysql://localhost:3306/school?useSSL=false";
    static String username = "root";
    static String password = "Abhishek@1137";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

//            getAllStudents(connection);
//            insertStudent(connection, 21, "Abhishek", 22, "CSE", 78);
//            insertBatchStudents(connection);
//            getStudentById(connection, 1);
//            getStudentsByBranch(connection, "CSE");
//            getStudentsByMarks(connection, 80);
//            getStudentsByAgeRange(connection, 20, 22);
//            updateStudentName(connection, 11, "Abhi");
//            updateBranchAndMarks(connection, 2, "IT", 88);
//            increaseMarksByBranch(connection, "CSE", 5);
//            deleteStudentById(connection, 20);
//            deleteStudentsBelowMarks(connection, 60);

            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertStudent(Connection connection, int id, String name, int age, String branch, int marks) throws SQLException {
        String query = "INSERT INTO student (id, name, age, branch, marks) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setInt(3, age);
        ps.setString(4, branch);
        ps.setInt(5, marks);
        int row = ps.executeUpdate();
        System.out.println("Row inserted : " + row);
    }

    public static void insertBatchStudents(Connection connection) throws SQLException {
        String query = "INSERT INTO student (id, name, age, branch, marks) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, 21);
        ps.setString(2, "Ravi");
        ps.setInt(3, 21);
        ps.setString(4, "ECE");
        ps.setInt(5, 70);
        ps.addBatch();

        ps.setInt(1, 22);
        ps.setString(2, "Sita");
        ps.setInt(3, 20);
        ps.setString(4, "CSE");
        ps.setInt(5, 85);
        ps.addBatch();

        ps.setInt(1, 23);
        ps.setString(2, "Kiran");
        ps.setInt(3, 22);
        ps.setString(4, "ME");
        ps.setInt(5, 65);
        ps.addBatch();

        ps.setInt(1, 24);
        ps.setString(2, "Meena");
        ps.setInt(3, 21);
        ps.setString(4, "IT");
        ps.setInt(5, 90);
        ps.addBatch();

        ps.setInt(1, 25);
        ps.setString(2, "Arjun");
        ps.setInt(3, 23);
        ps.setString(4, "CSE");
        ps.setInt(5, 75);
        ps.addBatch();

        int[] row = ps.executeBatch();
        System.out.println("Rows inserted : " + row.length);
    }

    public static void getAllStudents(Connection connection) throws SQLException {
        String query = "SELECT * FROM student";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getInt("age") + " " + rs.getString("branch") + " " + rs.getInt("marks"));
        }
    }

    public static void getStudentById(Connection connection, int id) throws SQLException {
        String query = "SELECT * FROM student WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getInt("age") + " " + rs.getString("branch") + " " + rs.getInt("marks"));
        }
    }

    public static void getStudentsByBranch(Connection connection, String branch) throws SQLException {
        String query = "SELECT * FROM student WHERE branch = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, branch);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("name"));
        }
    }

    public static void getStudentsByMarks(Connection connection, int marks) throws SQLException {
        String query = "SELECT * FROM student WHERE marks > ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, marks);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getInt("marks"));
        }
    }

    public static void getStudentsByAgeRange(Connection connection, int minAge, int maxAge) throws SQLException {
        String query = "SELECT * FROM student WHERE age BETWEEN ? AND ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, minAge);
        ps.setInt(2, maxAge);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getInt("age"));
        }
    }

    public static void updateStudentName(Connection connection, int id, String name) throws SQLException {
        String query = "UPDATE student SET name = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ps.setInt(2, id);
        int row = ps.executeUpdate();
        System.out.println("Row updated : " + row);
    }

    public static void updateBranchAndMarks(Connection connection, int id, String branch, int marks) throws SQLException {
        String query = "UPDATE student SET branch = ?, marks = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, branch);
        ps.setInt(2, marks);
        ps.setInt(3, id);
        int row = ps.executeUpdate();
        System.out.println("Row updated : " + row);
    }

    public static void increaseMarksByBranch(Connection connection, String branch, int increment) throws SQLException {
        String query = "UPDATE student SET marks = marks + ? WHERE branch = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, increment);
        ps.setString(2, branch);
        int row = ps.executeUpdate();
        System.out.println("Rows updated : " + row);
    }

    public static void deleteStudentById(Connection connection, int id) throws SQLException {
        String query = "DELETE FROM student WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        int row = ps.executeUpdate();
        System.out.println("Rows deleted : " + row);
    }

    public static void deleteStudentsBelowMarks(Connection connection, int marks) throws SQLException {
        String query = "DELETE FROM student WHERE marks < ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, marks);
        int row = ps.executeUpdate();
        System.out.println("Rows deleted : " + row);
    }
}
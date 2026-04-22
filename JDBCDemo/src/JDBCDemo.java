import java.sql.*;

public class JDBCDemo {

    static String url = "jdbc:mysql://localhost:3306/school?useSSL=false";
    static String username = "root";
    static String password = "Abhishek@1137";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            insertStudent(connection);
            readAllStudents(connection);
//            getHighScorers(connection);
//            sortStudentsByMarks(connection);
//            groupByBranch(connection);
//            getAverageMarks(connection);
//            updateStudentMarks(connection);
//            deleteLowScorers(connection);

            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static void insertStudent(Connection connection) {
        try {
            String query = "INSERT INTO student (id, name, age, branch, marks) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, 11);
            ps.setString(2, "NewStudent");
            ps.setInt(3, 22);
            ps.setString(4, "CSE");
            ps.setInt(5, 75);

            int rows = ps.executeUpdate();
            System.out.println("Inserted rows: " + rows);

        } catch (Exception e) {
            System.out.println("Insert Error: " + e.getMessage());
        }
    }


    public static void readAllStudents(Connection connection) {
        try {
            String query = "SELECT * FROM student";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getInt("age") + " | " +
                                rs.getString("branch") + " | " +
                                rs.getInt("marks")
                );
            }

        } catch (Exception e) {
            System.out.println("Read Error: " + e.getMessage());
        }
    }


    public static void getHighScorers(Connection connection) {
        try {
            String query = "SELECT * FROM student WHERE marks > ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, 80);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- High Scorers (Marks > 80) ---");
            while (rs.next()) {
                System.out.println(rs.getString("name") + " - " + rs.getInt("marks"));
            }

        } catch (Exception e) {
            System.out.println("Filter Error: " + e.getMessage());
        }
    }


    public static void sortStudentsByMarks(Connection connection) {
        try {
            String query = "SELECT * FROM student ORDER BY marks DESC";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\n--- Students Sorted by Marks ---");
            while (rs.next()) {
                System.out.println(rs.getString("name") + " - " + rs.getInt("marks"));
            }

        } catch (Exception e) {
            System.out.println("Sort Error: " + e.getMessage());
        }
    }


    public static void groupByBranch(Connection connection) {
        try {
            String query = "SELECT branch, COUNT(*) AS total FROM student GROUP BY branch";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\n--- Students per Branch ---");
            while (rs.next()) {
                System.out.println(rs.getString("branch") + " : " + rs.getInt("total"));
            }

        } catch (Exception e) {
            System.out.println("Group Error: " + e.getMessage());
        }
    }


    public static void getAverageMarks(Connection connection) {
        try {
            String query = "SELECT AVG(marks) AS avg_marks FROM student";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("\nAverage Marks: " + rs.getDouble("avg_marks"));
            }

        } catch (Exception e) {
            System.out.println("Aggregate Error: " + e.getMessage());
        }
    }


    public static void updateStudentMarks(Connection connection) {
        try {
            String query = "UPDATE student SET marks = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, 95); // new marks
            ps.setInt(2, 1);  // student id

            int rows = ps.executeUpdate();
            System.out.println("\nUpdated rows: " + rows);

        } catch (Exception e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }


    public static void deleteLowScorers(Connection connection) {
        try {
            String query = "DELETE FROM student WHERE marks < ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, 60);

            int rows = ps.executeUpdate();
            System.out.println("Deleted rows (marks < 60): " + rows);

        } catch (Exception e) {
            System.out.println("Delete Error: " + e.getMessage());
        }
    }
}
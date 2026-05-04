package org.example.leave.dao;

import org.example.leave.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaveApplicationDAO {
    private static final String INSERT_SQL = "INSERT INTO leave_applications " +
            "(employee_name, employee_id, department, leave_type, leave_days, reason, approval_message) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public void save(String employeeName, String employeeId, String department, String leaveType,
                     int leaveDays, String reason, String approvalMessage) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
                statement.setString(1, employeeName.trim());
                statement.setString(2, employeeId.trim());
                statement.setString(3, department.trim());
                statement.setString(4, leaveType.trim());
                statement.setInt(5, leaveDays);
                statement.setString(6, reason.trim());
                statement.setString(7, approvalMessage);
                statement.executeUpdate();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}



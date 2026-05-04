package org.example.leave.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.leave.dao.LeaveApplicationDAO;
import org.example.leave.util.RequestErrorUtil;
import org.example.leave.util.ValidationUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LeaveServlet extends HttpServlet {
    private transient LeaveApplicationDAO leaveApplicationDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        leaveApplicationDAO = new LeaveApplicationDAO();
        System.out.println("=== LeaveServlet Initialized ===");
        System.out.println("Servlet Name: " + this.getServletName());
        System.out.println("Servlet Config: " + this.getServletConfig());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("\n=== Processing Leave Form Request (GET) ===");
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Remote Address: " + request.getRemoteAddr());

        HttpSession session = request.getSession(false);
        if (session != null) {
            Object formError = session.getAttribute("formError");
            if (formError != null) {
                System.out.println("Flash Error Message: " + formError);
                request.setAttribute("formError", formError);
                session.removeAttribute("formError");
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/leave.jsp");
        try {
            System.out.println("Forwarding to leave.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            System.out.println("Error forwarding to leave.jsp: " + e.getMessage());
            throw new IOException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("\n=== Processing Leave Application Request (POST) ===");
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Remote Address: " + request.getRemoteAddr());

        String employeeName = request.getParameter("employeeName");
        String employeeId = request.getParameter("employeeId");
        String department = request.getParameter("department");
        String leaveType = request.getParameter("leaveType");
        String leaveDaysText = request.getParameter("leaveDays");
        String reason = request.getParameter("reason");

        System.out.println("\n--- Form Data Received ---");
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Department: " + department);
        System.out.println("Leave Type: " + leaveType);
        System.out.println("Leave Days: " + leaveDaysText);
        System.out.println("Reason: " + reason);

        if (!isValidRequest(employeeName, employeeId, department, leaveType, leaveDaysText, reason)) {
            System.out.println("Validation failed. Redirecting to leave form");
            RequestErrorUtil.redirectWithError(request, response, "Please fill all required fields correctly.");
            return;
        }

        int leaveDays = ValidationUtil.parseLeaveDays(leaveDaysText);
        String validationError = ValidationUtil.validate(employeeName, employeeId, department, leaveType, leaveDays, reason);
        if (validationError != null) {
            System.out.println("Business validation error: " + validationError);
            RequestErrorUtil.redirectWithError(request, response, validationError);
            return;
        }

        String approvalMessage = ValidationUtil.approvalMessage(leaveDays);
        System.out.println("Approval Message: " + approvalMessage);

        try {
            System.out.println("Saving leave application to database...");
            leaveApplicationDAO.save(employeeName, employeeId, department, leaveType, leaveDays, reason, approvalMessage);
            System.out.println("Leave application saved successfully");
        } catch (SQLException exception) {
            System.out.println("Database error while saving: " + exception.getMessage());
            getServletContext().log("Failed to save leave request", exception);
            RequestErrorUtil.redirectWithError(request, response,
                    "Unable to submit leave request right now. Please check the database connection and try again.");
            return;
        }

        System.out.println("\n--- Validation Passed ---");
        System.out.println("Setting request attributes and forwarding to review page");

        request.setAttribute("employeeName", employeeName.trim());
        request.setAttribute("employeeId", employeeId.trim());
        request.setAttribute("department", department.trim());
        request.setAttribute("leaveType", leaveType.trim());
        request.setAttribute("leaveDays", leaveDays);
        request.setAttribute("reason", reason.trim());
        request.setAttribute("approvalMessage", approvalMessage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/review.jsp");
        dispatcher.forward(request, response);
    }

    private boolean isValidRequest(String employeeName, String employeeId, String department,
                                   String leaveType, String leaveDaysText, String reason) {
        if (employeeName == null || employeeName.trim().isEmpty()) {
            System.out.println("Validation Error: Employee Name is empty");
            return false;
        }

        if (employeeId == null || employeeId.trim().isEmpty()) {
            System.out.println("Validation Error: Employee ID is empty");
            return false;
        }

        if (department == null || department.trim().isEmpty()) {
            System.out.println("Validation Error: Department is empty");
            return false;
        }

        if (leaveType == null || leaveType.trim().isEmpty()) {
            System.out.println("Validation Error: Leave Type is not selected");
            return false;
        }

        if (leaveDaysText == null || leaveDaysText.trim().isEmpty()) {
            System.out.println("Validation Error: Leave Days is empty");
            return false;
        }

        if (reason == null || reason.trim().isEmpty()) {
            System.out.println("Validation Error: Reason is empty");
            return false;
        }

        return true;
    }

    @Override
    public void destroy() {
        super.destroy();
        leaveApplicationDAO = null;
        System.out.println("=== LeaveServlet Destroyed ===");
        System.out.println("Servlet cleanup completed");
    }
}



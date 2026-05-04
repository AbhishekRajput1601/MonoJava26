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
    public void init() {
        leaveApplicationDAO = new LeaveApplicationDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object formError = session.getAttribute("formError");
            if (formError != null) {
                request.setAttribute("formError", formError);
                session.removeAttribute("formError");
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/leave.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeName = request.getParameter("employeeName");
        String employeeId = request.getParameter("employeeId");
        String department = request.getParameter("department");
        String leaveType = request.getParameter("leaveType");
        String leaveDaysText = request.getParameter("leaveDays");
        String reason = request.getParameter("reason");

        int leaveDays = ValidationUtil.parseLeaveDays(leaveDaysText);
        String validationError = ValidationUtil.validate(employeeName, employeeId, department, leaveType, leaveDays, reason);
        if (validationError != null) {
            RequestErrorUtil.redirectWithError(request, response, validationError);
            return;
        }

        String approvalMessage = ValidationUtil.approvalMessage(leaveDays);

        try {
            leaveApplicationDAO.save(employeeName, employeeId, department, leaveType, leaveDays, reason, approvalMessage);
        } catch (SQLException exception) {
            getServletContext().log("Failed to save leave request", exception);
            RequestErrorUtil.redirectWithError(request, response,
                    "Unable to submit leave request right now. Please check the database connection and try again.");
            return;
        }

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

    @Override
    public void destroy() {
        leaveApplicationDAO = null;
        getServletContext().log("LeaveServlet destroyed");
    }
}



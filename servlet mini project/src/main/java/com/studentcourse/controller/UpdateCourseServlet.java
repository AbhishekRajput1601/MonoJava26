package com.studentcourse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.model.Course;
import com.studentcourse.util.ValidationUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/updateCourse")
public class UpdateCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("UpdateCourse POST request received");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        String courseIdParam = request.getParameter("courseId");
        if (courseIdParam == null || courseIdParam.trim().isEmpty()) {
            String encoded = URLEncoder.encode("Invalid course ID.", StandardCharsets.UTF_8);
            response.sendRedirect("viewCourses?error=" + encoded);
            return;
        }

        int courseId;
        try {
            courseId = Integer.parseInt(courseIdParam);
        } catch (NumberFormatException e) {
            String encoded = URLEncoder.encode("Invalid course ID.", StandardCharsets.UTF_8);
            response.sendRedirect("viewCourses?error=" + encoded);
            return;
        }

        String courseName = request.getParameter("courseName");
        String duration = request.getParameter("duration");
        String feesStr = request.getParameter("fees");
        String trainerName = request.getParameter("trainerName");

        String errorMessage = "";
        if (courseName == null || courseName.trim().isEmpty()) {
            errorMessage += "Course name is required. ";
        }
        if (duration == null || duration.trim().isEmpty()) {
            errorMessage += "Duration is required. ";
        } else if (!ValidationUtil.isValidDuration(duration)) {
            errorMessage += "Duration must be greater than 0. ";
        }
        if (feesStr == null || feesStr.trim().isEmpty()) {
            errorMessage += "Fees is required. ";
        } else if (!ValidationUtil.isValidFees(feesStr)) {
            errorMessage += "Fees must be greater than 0. ";
        }
        if (trainerName == null || trainerName.trim().isEmpty()) {
            errorMessage += "Trainer name is required. ";
        } else if (!ValidationUtil.isValidTrainerName(trainerName)) {
            errorMessage += "Trainer name must contain letters only.";
        }

        if (!errorMessage.isEmpty()) {
            CourseDAO courseDAO = new CourseDAO();
            Course course = courseDAO.getCourseById(courseId);
            request.setAttribute("course", course);
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/views/update-course.jsp").forward(request, response);
            return;
        }

        Course course = new Course();
        course.setCourseId(courseId);
        course.setCourseName(courseName);
        course.setDuration(duration);
        course.setFees(Double.parseDouble(feesStr));
        course.setTrainerName(trainerName);

        CourseDAO courseDAO = new CourseDAO();
        if (courseDAO.updateCourse(course)) {
            response.sendRedirect("viewCourses");
        } else {
            Course fetchedCourse = courseDAO.getCourseById(courseId);
            request.setAttribute("course", fetchedCourse);
            request.setAttribute("errorMessage", "Failed to update course. Please try again.");
            request.getRequestDispatcher("/WEB-INF/views/update-course.jsp").forward(request, response);
        }
    }
}


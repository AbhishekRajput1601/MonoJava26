package com.studentcourse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.dao.CourseDAO;
import com.studentcourse.model.Student;
import com.studentcourse.model.Course;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/registrationForm")
public class RegistrationFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(RegistrationFormServlet.class.getName());

    @Override
    public void init() throws ServletException {
        LOGGER.info("RegistrationFormServlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("RegistrationForm GET request received");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();

        List<Student> studentList = studentDAO.getAllStudents();
        List<Course> courseList = courseDAO.getAllCourses();

        Object flashError = session.getAttribute("flashErrorMessage");
        if (flashError != null) {
            request.setAttribute("errorMessage", flashError.toString());
            session.removeAttribute("flashErrorMessage");
        }

        request.setAttribute("defaultRegistrationDate", LocalDate.now().toString());
        request.setAttribute("studentList", studentList);
        request.setAttribute("courseList", courseList);
        request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("RegistrationFormServlet destroyed");
    }
}

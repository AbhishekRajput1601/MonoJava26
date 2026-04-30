package com.code;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("========================================");
        System.out.println("Servlet Init: HelloServlet is initializing");
        System.out.println("Time: " + new java.util.Date());
        System.out.println("========================================");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("----------------------------------------");
        System.out.println("Servlet doGet: Handling GET request");
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Time: " + new java.util.Date());
        System.out.println("----------------------------------------");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out.println("<html>");
            out.println("<head><title>Hello Servlet</title></head>");
            out.println("<body>");
            out.println("<h2>Hello From Abhishek's Servlet!</h2>");
            out.println("<p>Servlet lifecycle methods are working:</p>");
            out.println("<ul>");
            out.println("<li><strong>init()</strong> - Called once when servlet is first initialized</li>");
            out.println("<li><strong>doGet()</strong> - Called every time a GET request is made</li>");
            out.println("<li><strong>destroy()</strong> - Called when servlet is removed from service</li>");
            out.println("</ul>");
            out.println("<p>Check the console/logs to see the lifecycle in action!</p>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    public void destroy() {
        System.out.println("========================================");
        System.out.println("Servlet Destroy: HelloServlet is being destroyed");
        System.out.println("Time: " + new java.util.Date());
        System.out.println("========================================");
        super.destroy();
    }
}

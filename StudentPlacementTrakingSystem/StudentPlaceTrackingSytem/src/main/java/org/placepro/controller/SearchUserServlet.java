package org.placepro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.placepro.dao.StudentDao;
import org.placepro.daoImpl.StudentDaoImpl;
import org.placepro.model.Users;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/searchUsers")
public class SearchUserServlet extends HttpServlet {
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String course = req.getParameter("course");
        String skill = req.getParameter("skill");
        Double percentage = null;
        if (req.getParameter("percentage") != null && !req.getParameter("percentage").isEmpty()) {
            percentage = Double.parseDouble(req.getParameter("percentage"));
        }

        List<Users> students = studentDao.searchUsers(course, percentage, skill);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><title>Search Results</title>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("</head><body><div class='container mt-4'>");
        out.println("<h2>Search Results</h2>");
        out.println("<table class='table table-bordered'><thead><tr><th>ID</th><th>Name</th><th>Email</th><th>Course</th><th>CGPA</th><th>Skills</th><th>Status</th></tr></thead><tbody>");
        for (Users u : students) {
            out.println("<tr>");
            out.println("<td>" + u.getId() + "</td>");
            out.println("<td>" + u.getName() + "</td>");
            out.println("<td>" + u.getEmail() + "</td>");
            out.println("<td>" + u.getCourse() + "</td>");
            out.println("<td>" + u.getPercentage() + "</td>");
            out.println("<td>" + u.getSkills() + "</td>");
            out.println("<td>" + u.getStatus() + "</td>");
            out.println("</tr>");
        }
        out.println("</tbody></table></div></body></html>");
    }


}

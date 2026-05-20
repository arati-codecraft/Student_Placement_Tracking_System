package org.placepro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.placepro.DBconfig.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addInterview")
public class AddInterviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html><html><head><title>Schedule Interview</title>");
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css'>");
        out.println("<link rel='stylesheet' href='CSS/Admin.css'>");
        out.println("</head><body>");

        out.println("<div class='container mt-4'>");
        out.println("<h2>Schedule Interview</h2>");
        out.println("<form method='post' action='addInterview'>");
        out.println("Placement ID: <input type='number' name='placementId' required><br><br>");
        out.println("Interview Date: <input type='date' name='date' required><br><br>");
        out.println("Interview Time: <input type='text' name='time' placeholder='HH:MM AM/PM'><br><br>");
        out.println("Location: <input type='text' name='location'><br><br>");
        out.println("Mode: <select name='mode'><option>Offline</option><option>Online</option></select><br><br>");
        out.println("Meeting Link (if online): <input type='text' name='link'><br><br>");
        out.println("<button type='submit'>Add Interview</button>");
        out.println("</form>");
        out.println("</div></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int placementId = Integer.parseInt(req.getParameter("placementId"));
        String date = req.getParameter("date");
        String time = req.getParameter("time");
        String location = req.getParameter("location");
        String mode = req.getParameter("mode");
        String link = req.getParameter("link");

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO interview(placement_id, interview_date, interview_time, location, mode, meeting_link, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, 'Scheduled')"
            );
            ps.setInt(1, placementId);
            ps.setString(2, date);
            ps.setString(3, time);
            ps.setString(4, location);
            ps.setString(5, mode);
            ps.setString(6, link);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect back to admin dashboard or interview list
        res.sendRedirect("admindashboard");
    }
}

package org.placepro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.placepro.DBconfig.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateApplicationStatus")
public class UpdateApplicationStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.html");
            return;
        }

        int appId = Integer.parseInt(req.getParameter("appId"));
        String status = req.getParameter("status");

        try (Connection con = DBConnection.getConnection()) {
            // Update applications
            PreparedStatement ps = con.prepareStatement("UPDATE applications SET status=? WHERE id=?");
            ps.setString(1, status);
            ps.setInt(2, appId);
            ps.executeUpdate();

            // Update placements
            PreparedStatement ps2 = con.prepareStatement("UPDATE placements SET status=? WHERE id=?");
            ps2.setString(1, status);
            ps2.setInt(2, appId);
            ps2.executeUpdate();

            // Insert notification
            PreparedStatement ps3 = con.prepareStatement(
                "INSERT INTO notifications(student_id, message) " +
                "SELECT a.student_id, CONCAT('Your application with ', c.name, ' has been ', ?) " +
                "FROM applications a JOIN company c ON a.company_id = c.id WHERE a.id=?"
            );
            ps3.setString(1, status);
            ps3.setInt(2, appId);
            ps3.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        res.sendRedirect("placementTracker?updated=1");
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPostt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

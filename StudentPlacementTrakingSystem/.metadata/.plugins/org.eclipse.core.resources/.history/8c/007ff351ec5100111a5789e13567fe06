package org.placepro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.placepro.daoImpl.PlacementDaoImpl;
import org.placepro.model.Placement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/placementTracker")
public class PlacementTrackerServlet extends HttpServlet {
    private PlacementDaoImpl dao = new PlacementDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // ✅ Fetch all placement applications with student + company details
        List<Placement> placements = dao.getAllApplications();

        // ✅ Your existing HTML code stays the same
        out.println("<!DOCTYPE html><html lang='en'><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Placement Tracker</title>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css'>");
        out.println("<link rel='stylesheet' href='CSS/Admin.css'>");
        out.println("</head><body>");

        // Sidebar + main content (unchanged)
        out.println("<div class='sidebar'>"
            + "<div class='sidebar-header'><h5 class='fw-bold text-primary mb-0'>Place<span class='text-muted'>Pro</span></h5></div>"
            + "<div class='sidebar-user'><div class='user-img'>A</div><div><div class='fw-bold' style='font-size:13px;'>Admin</div><div style='font-size:11px;color:#777;'>Administrator</div></div></div>"
            + "<div class='nav-label'>Navigation</div>"
            + "<a href='admindashboard'><i class='bi bi-speedometer2'></i> Dashboard</a>"
            + "<a href='managestudent'><i class='bi bi-people'></i> Manage Students</a>"
            + "<a href='managecompany'><i class='bi bi-building'></i> Manage Companies</a>"
            + "<a class='active' href='placementTracker'><i class='bi bi-journal-text'></i> Placement Tracker</a>"
            +" <a href='SuccessStories.html'><i class=\"bi bi-trophy\"></i>Success Stories</a>"
            +"<br>"
            + "<a class='logout' href='logout'><i class='bi bi-box-arrow-right'></i> Logout</a>"
            + "</div>");

        out.println("<div class='main'>");
        out.println("<div class='hero-banner'><h2>Placement Applications</h2><p>Track and update student placement statuses</p></div>");

        out.println("<div class='table-card'><h5>Placement Applications</h5>");
        out.println("<table class='table mt-3'>");
        out.println("<thead><tr>"
            + "<th>ID</th><th>Name</th><th>Email</th><th>Course</th><th>Education</th>"
            + "<th>Company</th><th>Package</th><th>Status</th><th>Actions</th>"
            + "</tr></thead><tbody>");

        for (Placement p : placements) {
            String badgeClass = "status-badge badge-applied";
            if ("Selected".equalsIgnoreCase(p.getStatus())) {
                badgeClass = "status-badge badge-selected";
            } else if ("Rejected".equalsIgnoreCase(p.getStatus())) {
                badgeClass = "status-badge badge-rejected";
            }

            out.println("<tr>");
            out.println("<td>" + p.getId() + "</td>");
            out.println("<td>" + p.getStudentName() + "</td>");
            out.println("<td>" + p.getEmail() + "</td>");
            out.println("<td>" + p.getCourse() + "</td>");
            out.println("<td>" + p.getEducation() + "</td>");
            out.println("<td>" + p.getCompanyName() + "</td>");
            out.println("<td>" + p.getPackageAmount() + "</td>");
            out.println("<td><span class='" + badgeClass + "'>" + p.getStatus() + "</span></td>");
            out.println("<td>"
                + "<a class='btn btn-success btn-sm' href='updatePlacementStatus?id=" + p.getId() + "&status=Selected'>Select</a> "
                + "<a class='btn btn-danger btn-sm' href='updatePlacementStatus?id=" + p.getId() + "&status=Rejected'>Reject</a>"
                + "</td>");
            out.println("</tr>");
        }

        out.println("</tbody></table></div></div></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }
}

package org.placepro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.placepro.dao.CompanyDao;
import org.placepro.daoImpl.CompanyDaoImpl;
import org.placepro.model.Company;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewmanagecompany")
public class ViewCompanyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CompanyDao companyDao = new CompanyDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        List<Company> companies = companyDao.getAllCompanies();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Company Listings</title>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("</head><body><div class='container mt-4'>");

        out.println("<h2>Company Listings</h2>");
        out.println("<a href='addCompanyForm.html' class='btn btn-primary mb-3'>Add New Company</a>");

        out.println("<table class='table table-bordered'>");
        out.println("<thead><tr><th>ID</th><th>Name</th><th>Role</th><th>Package</th><th>Location</th><th>Eligibility</th><th>Deadline</th><th>Status</th><th>Action</th></tr></thead>");
        out.println("<tbody>");
        for (Company c : companies) {
            out.println("<tr>");
            out.println("<td>" + c.getId() + "</td>");
            out.println("<td>" + c.getName() + "</td>");
            out.println("<td>" + c.getJobRole() + "</td>");
            out.println("<td>" + c.getPackageAmount() + "</td>");
            out.println("<td>" + c.getLocation() + "</td>");
            out.println("<td>" + c.getEligibilityCriteria() + "</td>");
            out.println("<td>" + c.getLastDateToApply() + "</td>");
            out.println("<td>" + c.getStatus() + "</td>");
            out.println("<td>");
            out.println("<a href='updateCompanyForm.jsp?id=" + c.getId() + "' class='btn btn-warning btn-sm'>Edit</a> ");
            out.println("<a href='deleteCompany?id=" + c.getId() + "' class='btn btn-danger btn-sm'>Delete</a>");
            out.println("</td>");
            out.println("</tr>");
        }
        out.println("</tbody></table>");
        out.println("</div></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}

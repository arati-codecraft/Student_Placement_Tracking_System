package org.placepro.controller;

import java.io.IOException;

import org.placepro.dao.CompanyDao;
import org.placepro.daoImpl.CompanyDaoImpl;
import org.placepro.model.Company;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addCompany")
public class AddCompanyServlet extends HttpServlet {
    private CompanyDao companyDao = new CompanyDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Company c = new Company();
        c.setName(req.getParameter("name"));
        c.setLocation(req.getParameter("location"));
        c.setPackageAmount(Double.parseDouble(req.getParameter("package")));
        c.setJobRole(req.getParameter("jobRole"));
        c.setEligibilityCriteria(req.getParameter("eligibility"));
        c.setLastDateToApply(java.sql.Date.valueOf(req.getParameter("lastDate")));
        c.setStatus("Open");

        companyDao.addCompany(c);
        resp.sendRedirect(req.getContextPath() + "/managecompany");
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // You can show an HTML form here if needed
        resp.sendRedirect("addCompanyForm.html");
    }
}

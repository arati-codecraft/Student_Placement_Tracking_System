package org.placepro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.placepro.dao.InterviewDao;
import org.placepro.daoImpl.InterviewDaoImpl;
import org.placepro.model.Interview;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentInterviewServlet
 */
@WebServlet("/studentInterviews")
public class StudentInterviewServlet extends HttpServlet {

    private InterviewDao dao = new InterviewDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html");

        try {
            int studentId = (int) req.getSession().getAttribute("userId");

            List<Interview> list = dao.getStudentInterviews(studentId);

            PrintWriter out = resp.getWriter();

            out.println("<h2>My Interviews</h2>");

            for (Interview i : list) {
                out.println("<p>");
                out.println("Company: " + i.getCompanyName() + "<br>");
                out.println("Date: " + i.getInterviewDate() + "<br>");
                out.println("Time: " + i.getInterviewTime() + "<br>");
                out.println("Mode: " + i.getMode() + "<br>");
                out.println("Location: " + i.getLocation() + "<br>");
                out.println("Status: " + i.getStatus());
                out.println("</p><hr>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

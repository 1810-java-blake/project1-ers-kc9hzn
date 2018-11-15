package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserController uc = new UserController();
	private ReimbursementController rc = new ReimbursementController();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		resp.addHeader("Access-Control-Allow-Headers", "Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
		
		String uri = req.getRequestURI();
		String context = "Project1";
		uri = uri.substring(context.length() + 2, uri.length());
		if (uri.startsWith("users")) {
			uc.process(req, resp);
		} else if (uri.startsWith("reimbursements")) {
			rc.process(req, resp);
		} else {
			resp.setStatus(404);
		}
	}
}

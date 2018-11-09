package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import com.revature.util.IntegerParser;
import com.revature.util.ResponseMapper;

public class ReimbursementController {
	private ReimbursementService rs = ReimbursementService.currentImplementation;
	private UserService us = UserService.currentImplementation;
	private ObjectMapper om = new ObjectMapper();
	
	void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String method = req.getMethod();
		
		switch (method) {
			case "GET":
				processGet(req, resp);
				break;
			case "POST":
				processPost(req, resp);
				break;
			default:
				resp.setStatus(404);
				break;
		}
	}
	
	private void processGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uri = req.getRequestURI();
		String context = "Project1";
		uri = uri.substring(context.length() + 2, uri.length());
		String[] uriArray = uri.split("/");
		if (uriArray.length == 1) {
			List<Reimbursement> reimbursements = rs.findAll();
			ResponseMapper.convertAndAttach(reimbursements, resp);
			return;
		} else if (uriArray.length == 2) {
			if (IntegerParser.isInteger(uriArray[1])) {
				Reimbursement reimbursement = rs.findById(Integer.valueOf(uriArray[1]));
				ResponseMapper.convertAndAttach(reimbursement, resp);
				return;
			} else {
				resp.setStatus(404);
				return;
			}
		} else if (uriArray.length == 3) {
			if ("user".equals(uriArray[1])) {
				if (IntegerParser.isInteger(uriArray[2])) {
					List<Reimbursement> reimbursements = rs.findAllByUser(us.getUserById(Integer.valueOf(uriArray[2])));
					ResponseMapper.convertAndAttach(reimbursements, resp);
					return;
				} else {
					resp.setStatus(404);
					return;
				}
			} else if ("status".equals(uriArray[1])) {
				if (IntegerParser.isInteger(uriArray[2])) {
					List<Reimbursement> reimbursements = rs.findAllByStatus(ReimbursementStatus.getStatus(Integer.valueOf(uriArray[2])));
					ResponseMapper.convertAndAttach(reimbursements, resp);
				} else {
					resp.setStatus(404);
					return;
				}
			} else {
				resp.setStatus(404);
				return;
			}
		} else {
			resp.setStatus(404);
			return;
		}
	}
	
	private void processPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uri = req.getRequestURI();
		String context = "Project1";
		uri = uri.substring(context.length() + 2, uri.length());
		String[] uriArray = uri.split("/");
		if (uriArray.length == 1) {
			Reimbursement r = om.readValue(req.getReader(), Reimbursement.class);
			rs.save(r);
			resp.getWriter().write("" + r.getId());
			resp.setStatus(201);
			return;
		} else if (uriArray.length == 2) {
			if (IntegerParser.isInteger(uriArray[1])) {
				int id = rs.findById(Integer.valueOf(uriArray[1])).getId();
				ReimbursementStatus status = om.readValue(req.getReader(), ReimbursementStatus.class);
				rs.resolveReimbursement(id, status);
				resp.getWriter().write("" + id + ", " + status);
				resp.setStatus(201);
				return;
			} else {
				resp.setStatus(404);
				return;
			}
		} else {
			resp.setStatus(404);
			return;
		}
	}
}

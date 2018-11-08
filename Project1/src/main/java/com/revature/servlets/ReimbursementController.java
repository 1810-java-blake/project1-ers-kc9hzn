package com.revature.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.util.ResponseMapper;

public class ReimbursementController {
	private ReimbursementService rs = ReimbursementService.currentImplementation;
	
	void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String method = req.getMethod();
		
		switch (method) {
			case "GET":
				processGet(req, resp);
				break;
			case "POST":
//				processPost(req, resp);
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
		}
	}
}

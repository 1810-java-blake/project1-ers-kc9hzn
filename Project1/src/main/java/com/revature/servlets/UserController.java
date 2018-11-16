package com.revature.servlets;

import java.io.IOException;
import com.revature.dto.Credential;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.services.UserService;
import com.revature.util.IntegerParser;
import com.revature.util.ResponseMapper;

public class UserController {
	private UserService us = UserService.currentImplementation;
	private ObjectMapper om = new ObjectMapper();
	
	void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String method = req.getMethod();
		if (method.equals("POST")) {
			processPost(req, resp);
			return;
		} else if (method.equals("GET")) {
			processGet(req, resp);
			return;
		} else if (method.equals("OPTIONS")) {
			return;
		} else {
			resp.setStatus(404);
		}
	}
	
	private void processPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uri = req.getRequestURI();
		String context = "Project1";
		uri = uri.substring(context.length() + 2, uri.length());
		String[] uriArray = uri.split("/");
		if (uriArray.length == 2) {
			if ("login".equals(uriArray[1])) {
				Credential c = om.readValue(req.getReader(), Credential.class);
				User user = us.login(c.getUsername(), c.getPassword(), req.getSession());
				if (user == null) {
					resp.setStatus(403);
				} else {
					ResponseMapper.convertAndAttach(user, resp);
					return;
				}
			} else {
				resp.setStatus(404);
			}
		} else {
			resp.setStatus(404);
		}
	}
	
	private void processGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uri = req.getRequestURI();
		String context = "Project1";
		uri = uri.substring(context.length() + 2, uri.length());
		String[] uriArray = uri.split("/");
		if (uriArray.length == 2) {
			if (IntegerParser.isInteger(uriArray[1])) {
				User u = us.getUserById(Integer.valueOf(uriArray[1]));
				ResponseMapper.convertAndAttach(u, resp);
				if (u == null) {
					resp.setStatus(404);
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
}

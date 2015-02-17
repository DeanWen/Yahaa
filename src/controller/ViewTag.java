package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class ViewTag extends Action{
	
	public String getName() {
		return "viewTag.do";
	}
	
	public String perform(HttpServletRequest request) {
		return "viewTag.jsp";
	}
}

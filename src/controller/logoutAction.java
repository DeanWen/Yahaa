package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;

public class logoutAction extends Action {
	public logoutAction(Model model) {
	}
	
	public String getName() {
		return "logout.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		session.invalidate();
		return "login.do";
	}
}
package controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import databeans.UserBean;
import model.Model;

public class Login extends Action {
	private UserDAO userDAO;
	
	public Login(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() {
		return "login.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		
		try {			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			if (username != null && username.trim().length() != 0) {
				UserBean user = userDAO.readByUserName(username);
				if (user != null && password != null && 
						password.trim().length() != 0 && user.matchPassword(password)) {
					session.setAttribute("user", user);
					return "home.do";
				}
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "login.jsp";	
	}
}

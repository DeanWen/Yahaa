package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import model.Model;
import databeans.UserBean;

public class SetPassword extends Action {
	private UserDAO userDAO;
	
	public SetPassword (Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() {
		return "setPassword.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		
		try {		
			String password = request.getParameter("password");
			UserBean user = (UserBean) session.getAttribute("user");
			if (user.getPassword() == null || user.getPassword().isEmpty()) {
				user.setPassword(password);
				userDAO.update(user);
			}
			return "home.do";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "register.jsp";
	}
}

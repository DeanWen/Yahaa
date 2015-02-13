package controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;

import model.Twitter;
import model.Model;

public class Login extends Action {
	
	public Login(Model model) {
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
//			if (errors.size() != 0) {
//				return "login.do";
//			}

			return "login.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			System.out.println(e);
			return "index.jsp";
		}
	}
}

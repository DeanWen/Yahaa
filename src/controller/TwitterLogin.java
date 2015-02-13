package controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;

import model.Flickr;
import model.Twitter;
import model.Model;

public class TwitterLogin extends Action {
	
	public TwitterLogin(Model model) {
	}

	public String getName() {
		return "twitterLogin.do";
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
			
			
			Twitter twitter = new Twitter();
			Token requestToken = twitter.getRequestToken();
			session.setAttribute("requestToken", requestToken);
			
			String url = twitter.getURL(requestToken);
			return url;
		} catch (Exception e) {
			errors.add(e.getMessage());
			System.out.println(e);
			return "index.jsp";
		}
	}
}

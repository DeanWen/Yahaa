package controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;

import model.Flickr;
import model.Model;

public class FlickrLogin extends Action {
	
	public FlickrLogin(Model model) {
	}

	public String getName() {
		return "flickrLogin.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		
		try {			
			Flickr flickr = new Flickr();
			Token requestToken = flickr.getRequestToken();
			session.setAttribute("requestToken", requestToken);
			
			String url = flickr.getURL(requestToken);
			return url;
		} catch (Exception e) {
			errors.add(e.getMessage());
			System.out.println(e);
			return "index.jsp";
		}
	}
}

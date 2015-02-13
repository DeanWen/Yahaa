package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Flickr;
import model.Model;
import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import formbeans.FlickrCallbackForm;

public class FlickrCallback extends Action{
	private FormBeanFactory<FlickrCallbackForm> formBeanFactory = FormBeanFactory
			.getInstance(FlickrCallbackForm.class);
	
	public FlickrCallback(Model model) {
		
	}
	
	public String getName() {
		return "flickrCallback.do";
	}
	
	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();

		try {
			FlickrCallbackForm form = formBeanFactory.create(request);

			Flickr flickr = new Flickr();
			Token requestToken = (Token) session.getAttribute("requestToken");

			if (!requestToken.getToken().equals(form.getOauth_token())) {
				errors.add("Invalid Request Token");
				return "login.jsp";
			}

			Verifier verifier = new Verifier(form.getOauth_verifier());
			Token accessToken = flickr.getAccessToken(requestToken, verifier);
			
			session.setAttribute("flickrAccessToken", accessToken);
			
			return "register.do";
		} catch (Exception e) {
			errors.add(e.getMessage());
			System.out.println(e);
			return "login.jsp";
		}
	}
}
 
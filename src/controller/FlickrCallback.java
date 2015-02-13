package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Flickr;
import model.Model;
import model.Twitter;

import org.mybeans.form.FormBeanFactory;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import formbeans.FlickrCallbackForm;
import formbeans.TwitterCallbackForm;

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

			//twitter.sendTwitter(accessToken, TwitterEncoder.encode("This is Tian Zheng testing twitter login using Yahaa"));
			//System.out.println(twitter.getTimeLine(accessToken));
			
			session.setAttribute("flickrAccessToken", accessToken);

			
			// return (String) session.getAttribute("curPage");
			return "home.do";
		} catch (Exception e) {
			errors.add(e.getMessage());
			System.out.println(e);
			return "login.jsp";
		}
	}
}
 
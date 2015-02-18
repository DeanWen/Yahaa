package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.Twitter;

import org.scribe.model.Token;

import DAO.UserDAO;
import databeans.UserBean;

public class RegisterAction extends Action {

	private Twitter twitter;
	private Token twitterToken;
	private Token flickrToken;
	private UserDAO userDAO;
	
	public RegisterAction(Model model) {
		twitter = model.getTwitter();
		model.getFlickr();
		userDAO = model.getUserDAO();
	}

	public String getName() { return "register.do"; }

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		twitterToken = (Token) session.getAttribute("twitterAccessToken");
		flickrToken = (Token) session.getAttribute("flickrAccessToken");
		
		try {	
			if (twitterToken == null) {
				return "twitterLogin.do";
			}
			if (flickrToken == null) {
				return "flickrLogin.do";
			}
			
			String twitterId = twitter.getTwitterId(twitterToken);
			String screenName = twitter.getUsername(twitterToken);
			
			UserBean user = userDAO.readByTwitterId(twitterId);
			if (user != null) {
				session.setAttribute("user", user);
				return "home.do";
			}else {
				user = new UserBean();
				user.setTwitterId(twitterId);
				user.setTwitterToken(twitterToken.getToken());
				user.setTwitterSecret(twitterToken.getSecret());
				user.setScreen_Name(screenName);
				user.setFlickrToken(flickrToken.getToken());
				user.setFlickrSecret(flickrToken.getSecret());
				userDAO.create(user);
				session.setAttribute("user", user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "register.jsp";
	}
}

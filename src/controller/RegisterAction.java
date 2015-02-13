package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Flickr;
import model.Model;
import model.Twitter;

import org.scribe.model.Token;

import DAO.UserDAO;
import databeans.UserBean;

public class RegisterAction extends Action {

	private Twitter twitter;
	private Flickr flickr;
	private Token twitterToken;
	private Token flickrToken;
	private UserDAO userDAO;
	
	public RegisterAction(Model model) {
		twitter = model.getTwitter();
		flickr = model.getFlickr();
		userDAO = model.getUserDAO();
	}

	public String getName() { return "register.do"; }

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		twitterToken = (Token) session.getAttribute("twitterAccessToken");
		flickrToken = (Token) session.getAttribute("flickrAccessToken");
		
		try {
			String twitterId = twitter.getTwitterId(twitterToken);
			String screenName = twitter.getUsername(twitterToken);
			
			UserBean user = userDAO.readByTwitterId(twitterId);

			if (user != null) {
				if (!user.getFlickrId().isEmpty() && !user.getTwitterId().isEmpty()) {
					session.setAttribute("user", user);
					return "home.do";
				}else {
					if (user.getFlickrId().isEmpty()) {
						return "flickrLogin.do";
					}else {
						return "twitterLogin.do";
					}
				}
			}else {
				user = new UserBean();
				user.setTwitterId(twitterId);
				user.setTwitterToken(twitterToken.getToken());
				user.setTwitterSecret(twitterToken.getSecret());
				user.setScreen_Name(screenName);
				userDAO.create(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "home.jsp";
	}
}

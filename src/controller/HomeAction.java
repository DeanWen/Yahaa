package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;
import org.xml.sax.SAXException;

import databeans.FlickrBean;
import databeans.TweetBean;
import databeans.UserBean;
import model.Flickr;
import model.FlickrPublic;
import model.Model;
import model.Twitter;

public class HomeAction extends Action {

	private Twitter twitter;
	private Flickr flickr;
	private Token twitterToken;
	private Token flickrToken;
	
	public HomeAction(Model model) {
		twitter = model.getTwitter();
		flickr = model.getFlickr();
	}

	public String getName() { return "home.do"; }

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		
		UserBean user = (UserBean) session.getAttribute("user");
		String tToken = user.getTwitterToken();
		String tSecret = user.getTwitterSecret();
		String fToken = user.getFlickrToken();
		String fSecret = user.getFlickrSecret();
		twitterToken = new Token(tToken, tSecret);
		flickrToken = new Token(fToken, fSecret);
		
//		twitterToken = (Token) session.getAttribute("twitterAccessToken");
//		flickrToken = (Token) session.getAttribute("flickrAccessToken");
		System.out.println("Hahahaha");
		System.out.println("flickr token: " + flickrToken);

		twitterToken = (Token) session.getAttribute("twitterAccessToken");
		flickrToken = (Token) session.getAttribute("flickrAccessToken");
		
		flickr.addFavourites(flickrToken, "16333373119");

		
		ArrayList<TweetBean> timeline = new ArrayList<TweetBean>();
		try {

			timeline = twitter.getTimeLine(twitterToken);

		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<FlickrBean> photos = new ArrayList<FlickrBean>();
		try {
			photos = flickr.fetchContactPhotos(flickrToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < photos.size(); i++) {
			String id = photos.get(i).getId();
			System.out.println(id);
			int likeCount = 0;
			try {
				likeCount = flickr.getFavoriteTotal(id, flickrToken);
			} catch (XPathExpressionException | IOException
					| ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			photos.get(i).setLikeCount(likeCount);
			System.out.println(likeCount);
		}
		
		int test = 0;
		try {
			test = flickr.getFavoriteTotal("16333373119", flickrToken);
		} catch (XPathExpressionException | IOException
				| ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(test);
		
		request.setAttribute("photos", photos);
		request.setAttribute("timeline", timeline);
		
		return "home.jsp";
	}
}

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

import databeans.TweetBean;
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
		twitterToken = (Token) session.getAttribute("twitterAccessToken");
		flickrToken = (Token) session.getAttribute("flickrAccessToken");
		System.out.println("Hahahaha");
		System.out.println("flickr token: " + flickrToken);
		
		ArrayList<TweetBean> timeline = new ArrayList<TweetBean>();
		try {

			timeline = twitter.getTimeLine(twitterToken);

		} catch (Exception e) {
			e.printStackTrace();
		}
		HashMap<String, String> photos = new HashMap<String, String>();
		try {
			photos = flickr.fetchContactPhotos(flickrToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Photos: " + photos);
		request.setAttribute("photos", photos);
		request.setAttribute("timeline", timeline);
		
		return "home.jsp";
	}
}

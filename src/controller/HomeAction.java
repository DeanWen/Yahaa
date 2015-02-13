package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;
import org.xml.sax.SAXException;

import model.Flickr;
import model.FlickrPublic;
import model.Model;
import model.Twitter;

public class HomeAction extends Action {

	private Twitter twitter;
	private FlickrPublic flickr;
	private Token twitterToken;
	private Token flickrToken;
	
	public HomeAction(Model model) {
		twitter = model.getTwitter();
		//flickr = model.getFlickr();
	}

	public String getName() { return "home.do"; }

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		twitterToken = (Token) session.getAttribute("twitterAccessToken");
		ArrayList<String> timeline = new ArrayList<String>();
		try {
			//twitter.fetchTweetsExample();
			timeline = twitter.getTimeLine(twitterToken);
			//flickr.fetchPlacesExample();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("timeline", timeline);
		return "home.jsp";
	}
}

package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import model.Flickr;
import model.Model;
import model.Twitter;

public class IndexAction extends Action {

	private Twitter twitter;
	private Flickr flickr;
	
	public IndexAction(Model model) {
		twitter = model.getTwitter();
		flickr = model.getFlickr();
	}

	public String getName() { return "index.do"; }

	public String perform(HttpServletRequest request) {
		try {
			//twitter.fetchTweetsExample();
			flickr.fetchPlacesExample();
		} catch (IOException | XPathExpressionException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		return "index.jsp";
	}
}

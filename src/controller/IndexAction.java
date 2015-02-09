package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
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
			
			ArrayList<String> photos = new ArrayList<String>();
			photos = flickr.fetchPhotoExample();
			request.setAttribute("photos", photos);
			
		} catch (IOException | XPathExpressionException | ParserConfigurationException | SAXException | XMLStreamException e) {
			e.printStackTrace();
		}
		return "index.jsp";
	}
}

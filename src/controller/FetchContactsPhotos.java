package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.scribe.model.Token;
import org.xml.sax.SAXException;

import model.Flickr;
import model.Model;

public class FetchContactsPhotos extends Action{
	private Flickr flickr;
	
	public FetchContactsPhotos(Model model) {
		flickr = model.getFlickr();
	}
	
	public String getName() {
		return "fetchContactsPhotos.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Token accessToken = (Token) session.getAttribute("flickrAccessToken");
		System.out.println("access token: " + accessToken);
		HashMap<String, String> photos = new HashMap<String, String>();
		try {
			photos = flickr.fetchContactPhotos(accessToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("photos", photos);
		
		return "photos.jsp";
	}
}

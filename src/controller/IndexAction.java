package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import model.FlickrPublic;
import model.Model;

public class IndexAction extends Action {

	private FlickrPublic flickr;
	public IndexAction(Model model) {
		model.getTwitter();
		flickr = model.getFlickrPublic();
		model.getUserDAO();
	}

	public String getName() { return "index.do"; }

	public String perform(HttpServletRequest request) {
		try {
			HashMap<String, String> photos = new HashMap<String, String>();
			photos = flickr.fetchPhotoExample();
			request.setAttribute("photos", photos);
		} catch (IOException | XPathExpressionException | ParserConfigurationException | SAXException | XMLStreamException e) {
			e.printStackTrace();
		}
		return "index.jsp";
	}
}

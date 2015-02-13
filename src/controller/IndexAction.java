package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import databeans.UserBean;
import DAO.UserDAO;
import model.Flickr;
import model.FlickrPublic;
import model.Model;
import model.Twitter;

public class IndexAction extends Action {

	private Twitter twitter;
	private FlickrPublic flickr;
	private UserDAO userDAO;
	public IndexAction(Model model) {
		twitter = model.getTwitter();
		flickr = model.getFlickrPublic();
		userDAO = model.getUserDAO();
	}

	public String getName() { return "index.do"; }

	public String perform(HttpServletRequest request) {
		try {
			UserBean user = new UserBean();
			user.setFlickrId("hello");
			userDAO.create(user);
			
			HashMap<String, String> photos = new HashMap<String, String>();
			photos = flickr.fetchPhotoExample();
			request.setAttribute("photos", photos);
		} catch (IOException | XPathExpressionException | ParserConfigurationException | SAXException | XMLStreamException e) {
			e.printStackTrace();
		}
		return "index.jsp";
	}
}

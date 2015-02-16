package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import model.Flickr;
import model.Model;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;
import org.xml.sax.SAXException;

import databeans.UserBean;
import formbeans.LikeFlickrForm;

public class LikeFlickrAction extends Action {
	private Flickr flickr;
	private Token flickrToken;
	private FormBeanFactory<LikeFlickrForm> likeFormFactory = FormBeanFactory
			.getInstance(LikeFlickrForm.class);
	private String flickrId;
	private String flickrTag;
	
	public LikeFlickrAction(Model model) {
		flickr = model.getFlickr();
	}


	public String getName() { return "likeFlickr.do"; }

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		String fToken = user.getFlickrToken();
		String fSecret = user.getFlickrSecret();
		flickrToken = new Token(fToken, fSecret);
		LikeFlickrForm form = null;
		boolean isFav = false;

		try {
			form = likeFormFactory.create(request);
		} catch (FormBeanException e) {
			e.printStackTrace();
		}
		flickrId = form.getId();
		
		try {
			isFav = flickr.isFavorite(flickrId, flickrToken);
		} catch (XPathExpressionException | IOException
				| ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		if (isFav) {
			flickr.removeFavourites(flickrToken, flickrId);
		}
		else {
			flickr.addFavourites(flickrToken, flickrId);
			String tag = "Yahaa";
			try {
				tag = flickr.getFlickrTag(flickrId, flickrToken);
			} catch (XPathExpressionException | IOException
					| ParserConfigurationException | SAXException e) {
				e.printStackTrace();
			}
			String curTime = flickr.getTime();
		}
					
		return "home.do";
	}
}

package controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.scribe.model.Token;
import databeans.FlickrBean;
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
		ArrayList<FlickrBean> photos = new ArrayList<FlickrBean>();
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

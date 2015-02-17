package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;

import model.Flickr;
import model.Model;

public class ViewGeography {
	private Flickr flickr;
	
	public ViewGeography(Model model) {
		flickr = model.getFlickr();
	}
	
	public String getName() {
		return "viewGeography.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Token accessToken = (Token) session.getAttribute("flickrAccessToken");
		ArrayList<Double> position = flickr.getGeoData("16260920778", accessToken);
		System.out.print(position.get(0) + " " + position.get(1));
		
		return "viewGeography.jsp";
	}
}

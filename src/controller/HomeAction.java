package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;
import org.xml.sax.SAXException;

import DAO.TagDAO;
import databeans.FlickrBean;
import databeans.LocationBean;
import databeans.TagBean;
import databeans.TweetBean;
import databeans.UserBean;
import model.Flickr;
import model.Model;
import model.Twitter;

public class HomeAction extends Action {

	private Twitter twitter;
	private Flickr flickr;
	private Token twitterToken;
	private Token flickrToken;
	private TagDAO tagDAO;
	
	public HomeAction(Model model) {
		twitter = model.getTwitter();
		flickr = model.getFlickr();
		tagDAO = model.getTagDAO();
	}

	public String getName() { return "home.do"; }

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			return "index.jsp";
		}
		
		UserBean user = (UserBean) session.getAttribute("user");
		String tToken = user.getTwitterToken();
		String tSecret = user.getTwitterSecret();
		String fToken = user.getFlickrToken();
		String fSecret = user.getFlickrSecret();
		twitterToken = new Token(tToken, tSecret);
		flickrToken = new Token(fToken, fSecret);
		
		LocationBean[] locations = null;
		try {
			locations = flickr.fetchPhotosLocation(flickrToken);
		} catch (XPathExpressionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		
//		double[] la = new double[locations.length];
//		double[] lo = new double[locations.length];
//		
//		for(int i = 0; i < locations.length; i++) {
//			la[i] = locations[i][0];
//			lo[i] = locations[i][1];
//		}
		System.out.println("locafsdfas:" + locations[0].latitude + locations[0].longitude);
//		System.out.println("locationssdfsdfadsfasd:" + la[0] + " " + lo[0]);
//		session.setAttribute("latitude", la[0]);
//		session.setAttribute("longitude", lo[0]);
		session.setAttribute("locations", locations);
		session.setAttribute("location", locations[0]);
		
//		twitterToken = (Token) session.getAttribute("twitterAccessToken");
//		flickrToken = (Token) session.getAttribute("flickrAccessToken");

		
		ArrayList<TweetBean> timeline = new ArrayList<TweetBean>();
		try {
			readData(user.getTwitterId(), request);
			timeline = twitter.getTimeLine(twitterToken);

		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<FlickrBean> photos = new ArrayList<FlickrBean>();
		try {
			photos = flickr.fetchContactPhotos(flickrToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		
		for (int i = 0; i < photos.size(); i++) {
			String id = photos.get(i).getId();
			System.out.println(id);
			int likeCount = 0;
			try {
				likeCount = flickr.getFavoriteTotal(id, flickrToken);
			} catch (XPathExpressionException | IOException
					| ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			photos.get(i).setLikeCount(likeCount);
		}
*/
		
		request.setAttribute("photos", photos);
		request.setAttribute("timeline", timeline);
		
		return "home.jsp";
	}
	
	public void readData(String userId, HttpServletRequest request) throws URISyntaxException {
		TagBean[] all = tagDAO.getAllbyUserId(userId);
		
		for (int i = 0; i < all.length - 1; i++) {
			for (int j = i + 1; j < all.length; j++) {
				if (all[i].getCount() < all[j].getCount()) {
					TagBean temp = all[j];
					all[i] = all[j];
					all[j] = temp;
				}
			}
		}
		
		try {
			File file = new File("tag.csv");
			String filePath = file.getCanonicalPath();
			System.out.println(filePath);
			HttpSession session = request.getSession();
			session.setAttribute("filePath", filePath);
			
			FileWriter outputPathFileWriter = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(outputPathFileWriter);
			writer.write("rank,tag,count");
			writer.newLine();
			for (int i = 0 ; i < all.length; i++) {
				writer.write((i+1) + "," + all[i].getTag() + "," + all[i].getCount());
				writer.newLine();
			}
			writer.close();
			
			/*Read File*/
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("tag.csv")));
			String nextline;
			while ((nextline = br.readLine()) != null) {
				System.out.println(nextline);// fastest the way to read and write
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

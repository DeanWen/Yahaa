package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import databeans.FlickrBean;

public class Flickr extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String API_KEY = "dd6594393674176f44ec5daeefdf86ac";
	private static final String API_SECRET = "2f25c2e619c6ec00";
	
	private static OAuthService service = null;
	private static Flickr flickr = new Flickr();
	
	public Flickr() {
		service = new ServiceBuilder()
        .provider(FlickrApi.class)
        .apiKey(API_KEY)
        .apiSecret(API_SECRET)
        .callback("http://localhost:8080/Yahaa/flickrCallback.do")
        .build();
		
	}
	
	public static Flickr getFlickr() {
		return flickr;
	}
	
	public Token getRequestToken() {
		Token requestToken = service.getRequestToken();
		return requestToken;	
	}
	
	public String getURL(Token requestToken) {
		String URL = service.getAuthorizationUrl(requestToken);
		return URL;
	}
	
	public Token getAccessToken(Token requestToken, Verifier verifier) {
		Token accessToken = service.getAccessToken(requestToken, verifier);
		return accessToken;
	}
	
	public boolean isFavorite(String id, Token accessToken) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
		HttpURLConnection connection = null;
		String query = "flickr.photos.getInfo";
		
		String address = "https://api.flickr.com/services/rest/?method=" + query + "&api_key=" + API_KEY 
				+ "&photo_id=" + id;
		OAuthRequest request = new OAuthRequest(Verb.GET, address);
		
		service.signRequest(accessToken, request);
		Response response = request.send();
		
		String filename = "tag.xml";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getStream()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
		
		String nextline;
		while ((nextline = br.readLine()) != null) {
			bw.write(nextline);// fastest the way to read and write
		}
		br.close();
		bw.close();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new FileInputStream(new File("tag.xml")));
		
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		
		Node node = (Node) xpath.evaluate("//photo", doc, XPathConstants.NODE);
		String isFav = "";

		if (node != null) {				
			isFav = (String) xpath.evaluate("@isfavorite", node, XPathConstants.STRING);
		}
		if (isFav.equals("0")) {
			return false;
		}
		else return true;				
	}
	
	public String getFlickrTag(String id, Token accessToken) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
		HttpURLConnection connection = null;
		String query = "flickr.photos.getInfo";
		
		String address = "https://api.flickr.com/services/rest/?method=" + query + "&api_key=" + API_KEY 
				+ "&photo_id=" + id;
		OAuthRequest request = new OAuthRequest(Verb.GET, address);
		
		service.signRequest(accessToken, request);
		Response response = request.send();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(response.getStream());
		
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		
		Node node = (Node) xpath.evaluate("//tags/tag", doc, XPathConstants.NODE);
		String tag = "";

		if (node != null) {				
			tag = (String) xpath.evaluate("@raw", node, XPathConstants.STRING);
		}
		if (tag.length() == 0) {
			tag = "Yahaa";
		}
		return tag;				
	}
	
	public int getFavoriteTotal(String id, Token accessToken) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {		
		HttpURLConnection connection = null;
		String query = "flickr.photos.getFavorites";
		URL url = new URL(
				"http://api.flickr.com/services/rest/?method=" + query + "&api_key=" + API_KEY 
				+ "&photo_id=" + id
		);
		
		String address = "https://api.flickr.com/services/rest/?method=" + query + "&api_key=" + API_KEY 
				+ "&photo_id=" + id;
		OAuthRequest request = new OAuthRequest(Verb.GET, address);
		
		service.signRequest(accessToken, request);
		Response response = request.send();
		
		String filename = "total.xml";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getStream()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
		
		String nextline;
		while ((nextline = br.readLine()) != null) {
			bw.write(nextline);// fastest the way to read and write
		}
		br.close();
		bw.close();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new FileInputStream(new File("total.xml")));
		
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		
		NodeList nodeList = (NodeList) xpath.evaluate("//photo", doc, XPathConstants.NODESET);
		
		Node node =nodeList.item(0);
			
		String total = (String) xpath.evaluate("@total", node, XPathConstants.STRING);
		int likeCount = Integer.parseInt(total);
		return likeCount;				
	}

	public ArrayList<Double> getGeoData(String id, Token accessToken) {
		ArrayList<Double> position = new ArrayList<Double>();
		String query = "flickr.photos.geo.getLocation";
		String address = "https://api.flickr.com/services/rest/?method=" + query + "&api_key=" + API_KEY
				+ "&photo_id=" + id + "&format=json&nojsoncallback=?";
		
		OAuthRequest request = new OAuthRequest(Verb.GET, address);
		service.signRequest(accessToken, request);
		Response response = request.send();
		
		JSONObject object = (JSONObject) JSONValue.parse(response.getBody());
		JSONObject entities = (JSONObject) object.get("location");
		double latitude = (double) entities.get("latitude");
		double longitude = (double) entities.get("longitude");
		
		position.add(latitude);
		position.add(longitude);
		return position;
	}
	public ArrayList<FlickrBean> fetchContactPhotosMethod(int count, Token accessToken) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
		ArrayList<FlickrBean> result = new ArrayList<FlickrBean>();
		
		String query = "flickr.photos.getContactsPhotos";
		String address = "https://api.flickr.com/services/rest/?method=" + query + "&api_key=" + API_KEY 
				+ "&count=" + count +"&include_self=" + 1;
		OAuthRequest request = new OAuthRequest(Verb.GET, address);
		
		service.signRequest(accessToken, request);
		Response response = request.send();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(response.getStream());
		
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		
		NodeList nodeList = (NodeList) xpath.evaluate("//photos/photo", doc, XPathConstants.NODESET);
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node =nodeList.item(i);
			
			String id = (String) xpath.evaluate("@id", node, XPathConstants.STRING);
			String server = (String) xpath.evaluate("@server", node, XPathConstants.STRING);
        	String secret = (String) xpath.evaluate("@secret", node, XPathConstants.STRING);
			String flickrurl = "http://static.flickr.com/" + server + "/" + id + "_" + secret + ".jpg";
			String title = (String) xpath.evaluate("@title", node, XPathConstants.STRING);
			
			FlickrBean tempBean = new FlickrBean();
			tempBean.setId(id);
			tempBean.setTitle(title);
			tempBean.setUrl(flickrurl);
			
			result.add(tempBean);
		}
		return result;
	}
	
	public ArrayList<FlickrBean> fetchContactPhotos(Token accessToken) throws XPathExpressionException, IOException, ParserConfigurationException, SAXException {
		return fetchContactPhotosMethod(10, accessToken);
	}

	public void addFavourites(Token accessToken, String photo_id) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.flickr.com/services/rest/?method=");
		query.append("flickr.favorites.add");
		query.append("&api_key=");
		query.append(API_KEY);
		query.append("&photo_id=");
		query.append(photo_id);
		
		System.out.println("URL: " + query.toString());
		System.out.println("Access Token" +  accessToken);
		OAuthRequest request = new OAuthRequest(Verb.POST, query.toString());
		
		try {
			service.signRequest(accessToken, request);
		} catch(Exception e) {
			System.out.print("ERROR: " + e.getMessage());
		}
		Response response = request.send();
	}
	
	public void removeFavourites(Token accessToken, String photo_id) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.flickr.com/services/rest/?method=");
		query.append("flickr.favorites.remove");
		query.append("&api_key=");
		query.append(API_KEY);
		query.append("&photo_id=");
		query.append(photo_id);
		
		OAuthRequest request = new OAuthRequest(Verb.POST, query.toString());		
		try {
			service.signRequest(accessToken, request);
		} catch(Exception e) {
			System.out.print("ERROR: " + e.getMessage());
		}
		Response response = request.send();
	}
	
	public String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH");
		Date current = new Date();
		String time = dateFormat.format(current);
		return time;
	}
	
}

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
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServlet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
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
	
	public HashMap<String, String> fetchContactPhotosMethod(int count, Token accessToken) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
		HashMap<String, String> result = new HashMap<String, String>();
		
		HttpURLConnection connection = null;
		String query = "flickr.photos.getContactsPhotos";
		URL url = new URL(
				"http://api.flickr.com/services/rest/?method=" + query + "&api_key=" + API_KEY 
				+ "&count=" + count +"&include_self=" + 1
		);
		
		String address = "https://api.flickr.com/services/rest/?method=" + query + "&api_key=" + API_KEY 
				+ "&count=" + count +"&include_self=" + 1;
		OAuthRequest request = new OAuthRequest(Verb.GET, address);
		
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println("URL: " + address);
		
		String filename = "photos.xml";
		
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
		Document doc = db.parse(new FileInputStream(new File("photos.xml")));
		
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
			
			result.put(flickrurl, title);
		}
		
		System.out.println(result.size() + " Photos");
		return result;
	}
	
	public HashMap<String, String> fetchContactPhotos(Token accessToken) throws XPathExpressionException, IOException, ParserConfigurationException, SAXException {
		return fetchContactPhotosMethod(10, accessToken);
	}
}

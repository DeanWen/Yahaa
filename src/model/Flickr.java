package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Flickr {
	private final String flickrKey;
    public Flickr (String key){ flickrKey = key; }  
    
    public static final String PLACE = "flickr.places.find";
    public static final String SEARCH = "flickr.photos.search";

    /**
     * Fetch places by keyword
     * @param keyword: keyword of place
     * @throws IOException 
     * @throws ParserConfigurationException 
     * @throws SAXException 
     * @throws XPathExpressionException 
     */
    public ArrayList<String> fetchPlaces(String keyword) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
    	ArrayList<String> result = new ArrayList<String>();
    	
    	HttpsURLConnection connection = null;
    	
    	URL url = new URL("https://api.flickr.com/services/rest/?method=" + PLACE + "&api_key=" + flickrKey + "&query=" + keyword + "&format=rest");
    	
    	connection = (HttpsURLConnection) url.openConnection();
    	connection.setDoOutput(true);
    	connection.setDoInput(true);
    	connection.setRequestMethod("GET");
    	connection.setUseCaches(false);
    	
    	String filename = "test.xml";
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
    	String nextline;
    	while ((nextline = br.readLine()) != null) {
    		bw.write(nextline);
    	}
    	br.close();
    	bw.close();
    	
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setNamespaceAware(true);
        
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new FileInputStream(new File("test.xml")));
       
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        
        NodeList nodeList = (NodeList) xpath.evaluate("//places/place",  doc, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
        	Node node = nodeList.item(i);
        	
        	String placeUrl = (String) xpath.evaluate("@place_url", node, XPathConstants.STRING);
        	
			String flickrurl = "https://www.flickr.com/places" + placeUrl;
        	System.out.println(flickrurl);
        }      
    	
    	return result;
    }
    
    public void fetchPlacesExample() throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
    	fetchPlaces("Nanjing");
    }
    
    
    /**
     * Fetch photos by keyword
     * @param keyword:  keyword of photos
     * @param count:      maximum amount of photos
     * @return:	list of urls of photos
     */
	public ArrayList<String> fetchPhotos(String keyword, int count) throws  IOException, XMLStreamException, XPathExpressionException, ParserConfigurationException, SAXException {
		ArrayList<String> result = new ArrayList<String>();
		
		HttpsURLConnection connection = null;
		
		URL url = new URL("https://api.flickr.com/services/rest/?method=" + SEARCH
				+ "&api_key=" + flickrKey + "&per_page=" + count
				+ "&text=" + keyword
				+ "&tag_mode=all&content_type=1&sort=relevance");
		
		connection = (HttpsURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		connection.setUseCaches(false);

		//writeRequest(connection, "");
		
		String filename = "test.xml";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
        Document doc = db.parse(new FileInputStream(new File("test.xml")));
 
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
		
        NodeList nodeList = (NodeList) xpath.evaluate("//photos/photo",  doc, XPathConstants.NODESET);
        
        String flickrurl = null;
        for (int i = 0; i < nodeList.getLength(); i++) {
        	Node node = nodeList.item(i);
        	
        	String id = (String) xpath.evaluate("@id", node, XPathConstants.STRING);
        	String server = (String) xpath.evaluate("@server", node, XPathConstants.STRING);
        	String secret = (String) xpath.evaluate("@secret", node, XPathConstants.STRING);
			flickrurl = "http://static.flickr.com/" + server + "/" + id + "_" + secret + ".jpg";
			result.add(flickrurl);
      		
        }
        
		return result;
	}
	
	public ArrayList<String> fetchPhotoExample () throws XPathExpressionException, IOException, XMLStreamException, ParserConfigurationException, SAXException {
		return fetchPhotos("Pittsburgh", 10);
	}
	
}

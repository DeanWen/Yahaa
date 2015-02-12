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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FlickrPublic {
	private final String flickrKey;
    public FlickrPublic (String key){ flickrKey = key; }  
    
    public static final String PLACE = "flickr.places.find";

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
    
}

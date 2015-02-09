package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Model {
	private final String twitterKey;
	private final String twitterSecret;
	private final String flickrKey;
	
	private final Twitter twitter;
	private final Flickr flickr;
	
	public Model(ServletConfig config) throws ServletException {
		twitterKey = config.getInitParameter("TwitterKey");
		twitterSecret  = config.getInitParameter("TwitterSecret");
		flickrKey =  config.getInitParameter("FlickerKey");
		
		twitter = new Twitter(twitterKey, twitterSecret);
		flickr = new Flickr(flickrKey);
	}

	public Twitter getTwitter () { return twitter; }
	public Flickr getFlickr () { return flickr; }
}

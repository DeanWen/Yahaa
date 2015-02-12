package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Model {
	private final String twitterKey;
	private final String twitterSecret;
	private final String flickrKey;
	private final String flickrSecret;
	
	private final Twitter twitter;
	private final FlickrPublic flickrPublic;
	
	public Model(ServletConfig config) throws ServletException {
		twitterKey = config.getInitParameter("TwitterKey");
		twitterSecret = config.getInitParameter("TwitterSecret");
		flickrKey = config.getInitParameter("FlickrKey");
		flickrSecret = config.getInitParameter("FlickrSecret");
		
		twitter = new Twitter();
		flickrPublic = new FlickrPublic(flickrKey);
	}

	public Twitter getTwitter () { return twitter; }
	public FlickrPublic getFlickr () { return flickrPublic; }

}

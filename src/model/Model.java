package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

import DAO.UserDAO;

public class Model {
	private final String twitterKey;
	private final String twitterSecret;
	private final String flickrKey;
	private final String flickrSecret;

	private final Twitter twitter;
	private final Flickr flickr;
	private final FlickrPublic flickrPublic;

	private UserDAO userDAO;

	public Model(ServletConfig config) throws ServletException {
		twitterKey = config.getInitParameter("TwitterKey");
		twitterSecret = config.getInitParameter("TwitterSecret");
		flickrKey = config.getInitParameter("FlickrKey");
		flickrSecret = config.getInitParameter("FlickrSecret");
		System.out.println(flickrKey);
		twitter = new Twitter();
		flickr = new Flickr();
		flickrPublic = new FlickrPublic(flickrKey);

		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL = config.getInitParameter("jdbcURL");
			String db_username = config.getInitParameter("db_username");
			String db_password = config.getInitParameter("db_password"); 
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL, db_username, db_password);
			userDAO = new UserDAO(pool, "User");
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public FlickrPublic getFlickrPublic() {
		return flickrPublic;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public Flickr getFlickr() {
		return flickr;
	}
}

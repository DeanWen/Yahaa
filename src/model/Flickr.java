package model;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class Flickr extends HttpServlet{
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
	
}

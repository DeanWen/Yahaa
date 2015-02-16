package model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import databeans.TweetBean;
import databeans.UserBean;
import DAO.UserDAO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class Twitter {
	private final String twitterKey = "SKdVaPTEnk8t3ccmpowHpkmxA";
	private final String twitterSecret = "XupqEu9OAlrsYryrszXdX0GN8uTHHdj01HPF2lKqW38eOK2Hz0";
	
	private static OAuthService service = null;
	
	private static Twitter twitter = new Twitter();
	
    public Twitter (){
    	service = new ServiceBuilder()
    			.provider(TwitterApi.SSL.class)
				.apiKey(twitterKey)
				.apiSecret(twitterSecret)
				.callback("http://localhost:8080/Yahaa/twitterCallback.do")
				.build();
    }  
	
	public static Twitter getTwitter() {
		return twitter;
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
	
	public String getTwitterId(Token accessToken) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/account/verify_credentials.json");
		
		OAuthRequest request = new OAuthRequest(Verb.GET, query.toString());
		service.signRequest(accessToken, request);
		Response response = request.send();

		String id_str = "";
		
		JSONObject object = (JSONObject) JSONValue.parse(response.getBody());
		id_str = (String) object.get("id_str");
		
		return id_str;
	}
	
	public String getUsername(Token accessToken) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/account/verify_credentials.json");
		
		OAuthRequest request = new OAuthRequest(Verb.GET, query.toString());
		service.signRequest(accessToken, request);
		Response response = request.send();

		String name = "";
		
		JSONObject object = (JSONObject) JSONValue.parse(response.getBody());
		name = (String) object.get("name");
		
		return name;
	}
	
	public ArrayList<TweetBean> getTimeLine(Token accessToken) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/statuses/home_timeline.json");
		
		OAuthRequest request = new OAuthRequest(Verb.GET, query.toString());
		service.signRequest(accessToken, request);
		
		Response response = request.send();
		ArrayList<TweetBean> result = new ArrayList<TweetBean>();
		
		JSONArray msgArray = (JSONArray) JSONValue.parse(response.getBody());

		Iterator<JSONObject> iterator = msgArray.iterator();
		while (iterator.hasNext()) {
			JSONObject next = iterator.next();
			String text = (String) next.get("text");
			Long count = (Long) next.get("favorite_count");
			Long id = (Long) next.get("id");
			TweetBean tempBean = new TweetBean();
			tempBean.setContent(text);
			tempBean.setLikeCount(count);
			tempBean.setId(id);
			result.add(tempBean);
		}
		
		return result;
	}
	
	public void like(Token accessToken, long tweetId) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/favorites/create.json?id=" + tweetId);

		OAuthRequest request = new OAuthRequest(Verb.POST, query.toString());
		service.signRequest(accessToken, request);
		
		Response response = request.send();

	}
	
	public void sendTwitter(Token accessToken, String text)
			throws UnsupportedEncodingException {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/statuses/update.json?status=");
		query.append(text);

		OAuthRequest request = new OAuthRequest(Verb.POST, query.toString());
		service.signRequest(accessToken, request);
		
		Response response = request.send();
		
		System.out.println("This is the response: " + response.getBody());
	}
	
}

package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import com.google.gson.Gson;

import databeans.UserBean;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class Twitter {
	private final String twitterKey = "SKdVaPTEnk8t3ccmpowHpkmxA";
	private final String twitterSecret = "XupqEu9OAlrsYryrszXdX0GN8uTHHdj01HPF2lKqW38eOK2Hz0";
	
	private static OAuthService service = null;
	
	private static Twitter twitter = new Twitter();
	
    public Twitter (){
    	service = new ServiceBuilder().provider(TwitterApi.SSL.class)
				.apiKey(twitterKey)
				.apiSecret(twitterSecret)
				.callback("http://localhost:8080/Task8Test/twitterCallback.do")
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
	
	public String getUsername(Token accessToken) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/account/settings.json");

		OAuthRequest request = new OAuthRequest(Verb.POST, query.toString());
		service.signRequest(accessToken, request);
		Response response = request.send();

		//System.out.println(response.getBody());
		Gson gson = new Gson();
		UserBean userBean = gson.fromJson(response.getBody(), UserBean.class);
		return userBean.getScreen_Name();
	}
	
	public ArrayList<String> getTimeLine(Token accessToken) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/statuses/home_timeline.json");
		
		OAuthRequest request = new OAuthRequest(Verb.GET, query.toString());
		service.signRequest(accessToken, request);
		Response response = request.send();
		ArrayList<String> result = new ArrayList<String>();
		
		JSONArray msgArray = (JSONArray) JSONValue.parse(response.getBody());

		Iterator<JSONObject> iterator = msgArray.iterator();
		while (iterator.hasNext()) {
			JSONObject next = iterator.next();
			String text = (String) next.get("text");
			result.add(text);
		}
		
		return result;
	}
	
	public void like(Token accessToken, long tweetId) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/favorites/create.json?id=" + tweetId);

		OAuthRequest request = new OAuthRequest(Verb.POST, query.toString());
		service.signRequest(accessToken, request);
		Response response = request.send();

		System.out.println(response.getBody());
	}
	
	public void sendTwitter(Token accessToken, String text)
			throws UnsupportedEncodingException {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/statuses/update.json?status=");
		query.append(text);

		OAuthRequest request = new OAuthRequest(Verb.POST, query.toString());
		service.signRequest(accessToken, request);
		//System.out.println(request);
		
		Response response = request.send();
		
		System.out.println("This is the response: " + response.getBody());
	}
	
}

package model;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			boolean favorite = (boolean) next.get("favorited");
			TweetBean tempBean = new TweetBean();
			tempBean.setContent(text);
			tempBean.setLikeCount(count);
			tempBean.setId(id);
			tempBean.setFavorited(favorite);
			result.add(tempBean);
		}
		
		return result;
	}
	
	public boolean isLiked(Token accessToken, long tweetId) {
		boolean liked = false;
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/statuses/show/" + tweetId + ".json");
		
		OAuthRequest request = new OAuthRequest(Verb.GET, query.toString());
		service.signRequest(accessToken, request);
		
		Response response = request.send();
		JSONObject object = (JSONObject) JSONValue.parse(response.getBody());
		boolean favorited = (boolean) object.get("favorited");
		if (favorited) liked = true;
		
		return liked;
	}
	
	public void like(Token accessToken, long tweetId) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/favorites/create.json?id=" + tweetId);

		OAuthRequest request = new OAuthRequest(Verb.POST, query.toString());
		service.signRequest(accessToken, request);
		
		Response response = request.send();
	}
	
	public void unLike(Token accessToken, long tweetId) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/favorites/destroy.json?id=" + tweetId);

		OAuthRequest request = new OAuthRequest(Verb.POST, query.toString());
		service.signRequest(accessToken, request);
		
		Response response = request.send();
	}
	
	public String getTag(Token accessToken, long tweetId) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/statuses/show/" + tweetId + ".json");
		
		OAuthRequest request = new OAuthRequest(Verb.GET, query.toString());
		service.signRequest(accessToken, request);
		
		Response response = request.send();
		
		JSONObject object = (JSONObject) JSONValue.parse(response.getBody());
		JSONObject entities = (JSONObject) object.get("entities");
		JSONArray hashtags = (JSONArray) entities.get("hashtags");
		String tag = "Yahaa";
		if (hashtags.size() != 0) {
			JSONObject firstTag = (JSONObject) hashtags.get(0);
			tag = (String) firstTag.get("text");	
		}		
		return tag;
	}
	
	public String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH");
		Date current = new Date();
		String time = dateFormat.format(current);
		return time;
	}
	
	public void sendTwitter(Token accessToken, String text)
			throws UnsupportedEncodingException {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/statuses/update.json?status=");
		String encodeText = TwitterEncoder.encode(text);
		query.append(encodeText);

		OAuthRequest request = new OAuthRequest(Verb.POST, query.toString());
		service.signRequest(accessToken, request);
		Response response = request.send();
		
	}

	public long getFavoriteTotal(long tweetId, Token accessToken) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/statuses/show/" + tweetId + ".json");
		
		OAuthRequest request = new OAuthRequest(Verb.GET, query.toString());
		service.signRequest(accessToken, request);
		
		Response response = request.send();
		
		JSONObject object = (JSONObject) JSONValue.parse(response.getBody());
		long count = (long) object.get("favorite_count");	
		return count;
	}
	
}

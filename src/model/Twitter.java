package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class Twitter {
	private final String twitterKey;
	private final String twitterSecret;
	
    public Twitter (String key, String secret){
    	twitterKey = key;
    	twitterSecret = secret;
    }  
    
    /**
     * Generate Base64 code of keys for OAuth
     */
	private String encodeKeys() {
		try {
			String encodedConsumerKey = URLEncoder.encode(twitterKey, "UTF-8");
			String encodedConsumerSecret = URLEncoder.encode(twitterSecret,"UTF-8");

			String fullKey = encodedConsumerKey + ":" + encodedConsumerSecret;
			byte[] encodedBytes = Base64.encodeBase64(fullKey.getBytes());

			return new String(encodedBytes);
		} catch (UnsupportedEncodingException e) {
			return new String();
		}
	}
	
    /**
     * Application-Only Authentication
     */
	public String getAuthenticationToken(String oauthUrl) throws IOException {
		HttpsURLConnection connection = null;
		String encodedCredentials = encodeKeys();

		try {
			URL url = new URL(oauthUrl);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Host", "api.twitter.com");
			connection.setRequestProperty("User-Agent", "InfinityWebService");
			connection.setRequestProperty("Authorization", "Basic "	+ encodedCredentials);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			connection.setRequestProperty("Content-Length", "29");
			connection.setUseCaches(false);

			writeRequest(connection, "grant_type=client_credentials");

			// Parse the JSON response into a JSON mapped object to fetch fields
			JSONObject obj = (JSONObject) JSONValue.parse(readResponse(connection));

			if (obj != null) {
				String tokenType = (String) obj.get("token_type");
				String token = (String) obj.get("access_token");

				return ((tokenType.equals("bearer")) && (token != null)) ? token : "";
			}
			return new String();
		} catch (MalformedURLException e) {
			throw new IOException("Invalid endpoint URL specified.", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
    /**
     * User Login Authentication
     */
	public String getToken(String oauthUrl) throws IOException {
		HttpsURLConnection connection = null;

		try {
			URL url = new URL(oauthUrl);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Host", "api.twitter.com");
			connection.setRequestProperty("oauth_callback", "http://localhost:8080/Yahaa/");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			connection.setUseCaches(false);

			writeRequest(connection, "grant_type=client_credentials");

			// Parse the JSON response into a JSON mapped object to fetch fields
			JSONObject obj = (JSONObject) JSONValue.parse(readResponse(connection));

			if (obj != null) {
				String tokenType = (String) obj.get("token_type");
				String token = (String) obj.get("access_token");

				return ((tokenType.equals("bearer")) && (token != null)) ? token : "";
			}
			return new String();
		} catch (MalformedURLException e) {
			throw new IOException("Invalid endpoint URL specified.", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
    /**
     * Fetch tweets
     */
	public ArrayList<String> fetchTimelineTweets(String apiUrl) throws IOException {
		HttpsURLConnection connection = null;
		// get OAuth Token
		String token = getToken("https://api.twitter.com/oauth/request_token");
		
		try {
			URL url = new URL(apiUrl);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Host", "api.twitter.com");
			connection.setRequestProperty("User-Agent", "InfinityWebService");
			connection.setRequestProperty("Authorization", "Oauth " + token);
			connection.setUseCaches(false);

			JSONObject obj = (JSONObject) JSONValue.parse(readResponse(connection));

			JSONArray msg = (JSONArray) obj.get("statuses");
			Iterator<JSONObject> iterator = msg.iterator();

			ArrayList<String> resultArrayList = new ArrayList<String>();
			while (iterator.hasNext()) {
				JSONObject next = iterator.next();
				String text = (String) next.get("text");
				resultArrayList.add(text);
			}

			return resultArrayList;
		} catch (MalformedURLException e) {
			throw new IOException("Invalid endpoint URL specified.", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	public void fetchTweetsExample () throws IOException {
		String queryUrlString = "https://api.twitter.com/1.1/search/tweets.json?q="	
				+ URLEncoder.encode("Kobe Bryant",   "UTF-8") + "&count="+ 3 +"&result_type=popular";
		System.out.println(fetchTimelineTweets(queryUrlString));
		
	}
	
	/**
	 * Executing an HTTPS request
	 * @param body: The content of HTTPS body
	 */
	private boolean writeRequest(HttpsURLConnection connection, String body) {
		try {
			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
			wr.write(body);
			wr.flush();
			wr.close();

			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Read an HTTPS response
	 * @return: a string of response message
	 */
	private String readResponse(HttpsURLConnection connection) {
		try {
			StringBuilder str = new StringBuilder();

			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				str.append(line + System.getProperty("line.separator"));
			}
			return str.toString();
		} catch (IOException e) {
			return new String();
		}
	}
}

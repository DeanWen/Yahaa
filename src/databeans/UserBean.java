package databeans;

/*
 * Dian Wen
 * 2/12/2015
 * 
 * */
import java.io.Serializable;
import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String twitterId = null;
	private String twitterToken = null;
	private String flickrId = null;
	private String flickrToken = null;
	private String screen_Name = null;
	private int likeGiven;
	private int likeReceived;
	private String password = null;
	private String twitterSecret = null;
	private String flickrSecret = null;
	
	public String getTwitterSecret() {
		return twitterSecret;
	}

	public void setTwitterSecret(String twitterSecret) {
		this.twitterSecret = twitterSecret;
	}

	public String getFlickrSecret() {
		return flickrSecret;
	}

	public void setFlickrSecret(String flickrSecret) {
		this.flickrSecret = flickrSecret;
	}

	public int getLikeGiven() {
		return likeGiven;
	}
	
	public boolean matchPassword (String password) {
		return this.password.equals(password);
	}
	
	public void setLikeGiven(int likeGiven) {
		this.likeGiven = likeGiven;
	}

	public int getLikeReceived() {
		return likeReceived;
	}

	public void setLikeReceived(int likeReceived) {
		this.likeReceived = likeReceived;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getTwitterToken() {
		return twitterToken;
	}

	public void setTwitterToken(String twitterToken) {
		this.twitterToken = twitterToken;
	}

	public String getFlickrId() {
		return flickrId;
	}

	public void setFlickrId(String flickrId) {
		this.flickrId = flickrId;
	}

	public String getFlickrToken() {
		return flickrToken;
	}

	public void setFlickrToken(String flickrToken) {
		this.flickrToken = flickrToken;
	}

	public String getScreen_Name() {
		return screen_Name;
	}

	public void setScreen_Name(String screen_Name) {
		this.screen_Name = screen_Name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTwitterSecret() {
		return twitterSecret;
	}

	public void setTwitterSecret(String twitterSecret) {
		this.twitterSecret = twitterSecret;
	}

	public String getFlickrSecret() {
		return flickrSecret;
	}

	public void setFlickrSecret(String flickrSecret) {
		this.flickrSecret = flickrSecret;
	}
}
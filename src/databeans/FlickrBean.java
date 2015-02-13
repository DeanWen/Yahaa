package databeans;

public class FlickrBean {
	private String id;
	private String url;
	private int likeCount;
	private String title;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	} 
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
}

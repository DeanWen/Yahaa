package databeans;

public class FlickrBean {
	private static final long serialVersionUID = 1L;

	private long id;
	private String url;
	private long likeCount;
	private String title;

	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	
	public long getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}
}

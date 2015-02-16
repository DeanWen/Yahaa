package databeans;

import java.io.Serializable;

public class TweetBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private long likeCount;
	private String content;
	//private boolean favorited;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
//	public boolean getFavorited() {
//		return favorited;
//	}
//	public void setFavorited(boolean favorite){
//		this.favorited = favorite;
//	}
	
}
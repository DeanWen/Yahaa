package databeans;
import java.io.Serializable;


import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class LikeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	int id;
	String userId;
	String contentId;
	String timestamp;
	int count;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}

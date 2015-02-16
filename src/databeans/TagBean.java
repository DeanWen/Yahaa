package databeans;

import java.io.Serializable;
import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class TagBean implements Serializable {
	private static final long serialVersionUID = 1L;
	int id;
	String userId;
	String tag;
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
package databeans;

import java.io.Serializable;

public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int userId;

	private String screen_name;

	public int getUserId() {
		return userId;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
}
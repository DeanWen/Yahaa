package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LikeTweetForm extends FormBean{
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public long getIdAsLong() {
		// Be sure to first call getValidationErrors() to ensure
		// that NullPointer exception or NumberFormatException will not be
		// thrown!
		return Long.parseLong(id);
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		return errors;
	}
}

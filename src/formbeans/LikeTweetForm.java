package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LikeTweetForm extends FormBean{
	private long id;

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		return errors;
	}
}

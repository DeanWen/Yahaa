package formbeans;

import org.mybeans.form.FormBean;


public class SendTwitterForm extends FormBean {
	private String text;
	
	public void setText(String content) {
		text = content;
	}
	
	public String getText() {
		return text;
	}
}

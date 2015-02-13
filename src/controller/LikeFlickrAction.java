package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Flickr;
import model.Model;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;

import databeans.UserBean;
import formbeans.LikeFlickrForm;

public class LikeFlickrAction extends Action {
	private Flickr flickr;
	private Token flickrToken;
	private FormBeanFactory<LikeFlickrForm> likeFormFactory = FormBeanFactory
			.getInstance(LikeFlickrForm.class);
	private String flickrId;
	
	public LikeFlickrAction(Model model) {
		flickr = model.getFlickr();
	}


	public String getName() { return "likeFlickr.do"; }

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		String fToken = user.getFlickrToken();
		String fSecret = user.getFlickrSecret();
		flickrToken = new Token(fToken, fSecret);
		LikeFlickrForm form = null;
		try {
			form = likeFormFactory.create(request);
			flickrId = form.getId();
			flickr.addFavourites(flickrToken, flickrId);
		} catch (FormBeanException e) {
			e.printStackTrace();
		}
		
		return "home.do";
	}
}

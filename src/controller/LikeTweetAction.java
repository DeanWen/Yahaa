package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;

import databeans.UserBean;
import formbeans.LikeTweetForm;
import model.Model;
import model.Twitter;

public class LikeTweetAction extends Action {

	private Twitter twitter;
	private Token twitterToken;
	private FormBeanFactory<LikeTweetForm> likeFormFactory = FormBeanFactory
			.getInstance(LikeTweetForm.class);
	private long tweetId;
	
	public LikeTweetAction(Model model) {
		twitter = model.getTwitter();
	}

	@Override
	public String getName() { return "likeTweet.do"; }

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		String tToken = user.getTwitterToken();
		String tSecret = user.getTwitterSecret();
		twitterToken = new Token(tToken, tSecret);

		LikeTweetForm form = null;
		try {
			form = likeFormFactory.create(request);
			tweetId = form.getIdAsLong();
			twitter.like(twitterToken, tweetId);
		} catch (FormBeanException e) {
			e.printStackTrace();
		}
		
		return "home.do";
	}
}

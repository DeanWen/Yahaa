package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;

import databeans.FlickrBean;
import databeans.TweetBean;
import formbeans.LikeTweetForm;
import model.Flickr;
import model.Model;
import model.Twitter;

public class LikeTweetAction extends Action {

	private Twitter twitter;
	private Token twitterToken;
	private FormBeanFactory<LikeTweetForm> buyFormFactory = FormBeanFactory
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
		twitterToken = (Token) session.getAttribute("twitterAccessToken");
		LikeTweetForm form = null;
		try {
			form = buyFormFactory.create(request);
			tweetId = form.getId();
			twitter.like(twitterToken, tweetId);
		} catch (FormBeanException e) {
			e.printStackTrace();
		}
		
		return "home.jsp";
	}
}

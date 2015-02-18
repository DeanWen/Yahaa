package controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.scribe.model.Token;
import databeans.UserBean;
import model.Model;
import model.Twitter;

public class SendTweetAction extends Action {

	private Twitter twitter;
	private Token twitterToken;
	
	public SendTweetAction(Model model) {
		twitter = model.getTwitter();
	}

	@Override
	public String getName() { return "sendTweet.do"; }

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		String tToken = user.getTwitterToken();
		String tSecret = user.getTwitterSecret();
		twitterToken = new Token(tToken, tSecret);
		String like = Integer.toString(user.getLikeGiven());
		String send = "I have given " + like + " likes on Yahaa. Come join me!";
		try {
			System.out.println("I am sending@!!!!!");
			twitter.sendTwitter(twitterToken, send);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
				
		return "rank.do";
	}
}

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;
import org.scribe.model.Verifier;

import model.TwitterEncoder;
import formbeans.TwitterCallbackForm;
import model.Model;
import model.Twitter;

public class TwitterCallback extends Action {
	private FormBeanFactory<TwitterCallbackForm> formBeanFactory = FormBeanFactory
			.getInstance(TwitterCallbackForm.class);
	
	public TwitterCallback(Model model) {

	}

	public String getName() {
		return "twitterCallback.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();

		try {
			TwitterCallbackForm form = formBeanFactory.create(request);

			Twitter twitter = Twitter.getTwitter();
			Token requestToken = (Token) session.getAttribute("requestToken");

			if (!requestToken.getToken().equals(form.getOauth_token())) {
				errors.add("Invalid Request Token");
				return "login.jsp";
			}

			Verifier verifier = new Verifier(form.getOauth_verifier());
			Token accessToken = twitter.getAccessToken(requestToken, verifier);

			twitter.sendTwitter(accessToken, TwitterEncoder.encode("This is Tian Zheng testing twitter login using Yahaa"));

			session.setAttribute("accessToken", accessToken);

			
			// return (String) session.getAttribute("curPage");
			return "index.do";
		} catch (Exception e) {
			errors.add(e.getMessage());
			System.out.println(e);
			return "login.jsp";
		}
	}
}
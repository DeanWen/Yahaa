package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;
import org.xml.sax.SAXException;

import databeans.LikeBean;
import databeans.TagBean;
import databeans.UserBean;
import formbeans.LikeTweetForm;
import model.Model;
import model.Twitter;
import DAO.LikeDAO;
import DAO.UserDAO;
import DAO.TagDAO;

public class LikeTweetAction extends Action {

	private Twitter twitter;
	private Token twitterToken;
	private FormBeanFactory<LikeTweetForm> likeFormFactory = FormBeanFactory
			.getInstance(LikeTweetForm.class);
	private long tweetId;
	private UserDAO userDAO;
	private TagDAO tagDAO;
	private LikeDAO likeDAO;
	
	public LikeTweetAction(Model model) {
		twitter = model.getTwitter();
		userDAO = model.getUserDAO();
		tagDAO = model.getTagDAO();
		likeDAO = model.getLikeDAO();
	}

	@Override
	public String getName() { return "likeTweet.do"; }

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		try {
			userDAO.hitLike(user);
		} catch (RollbackException e1) {
			e1.printStackTrace();
		}
		String tToken = user.getTwitterToken();
		String tSecret = user.getTwitterSecret();
		twitterToken = new Token(tToken, tSecret);

		LikeTweetForm form = null;
		try {
			form = likeFormFactory.create(request);
			tweetId = form.getIdAsLong();
			if (twitter.isLiked(twitterToken, tweetId)) {
				twitter.unLike(twitterToken, tweetId);
			} else {
				twitter.like(twitterToken, tweetId);
			}			
			String tag = twitter.getTag(twitterToken, tweetId);
			
			String twitterId = user.getTwitterId();
			TagBean tagBean = tagDAO.readById(twitterId, tag);
			if (tagBean == null) {
				TagBean newTagBean = new TagBean();
				newTagBean.setCount(1);
				newTagBean.setUserId(twitterId);
				newTagBean.setTag(tag);
				tagDAO.create(newTagBean);
			}
			else {
				int count = tagBean.getCount();
				tagBean.setCount(count + 1);
				try {
					tagDAO.update(tagBean);
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			long count = twitter.getFavoriteTotal(tweetId, twitterToken);
			String time = twitter.getTime();
			LikeBean likeBean = new LikeBean();
			likeBean.setUserId(twitterId);
			likeBean.setTimestamp(time);
			likeBean.setContentId(twitterId);
			likeBean.setCount((int)count);
			likeDAO.create(likeBean);
			
		} catch (FormBeanException e) {
			e.printStackTrace();
		}
		
		
		return "home.do";
	}
}

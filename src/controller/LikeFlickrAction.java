package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import model.Flickr;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;
import org.xml.sax.SAXException;

import DAO.LikeDAO;
import DAO.TagDAO;
import DAO.UserDAO;
import databeans.LikeBean;
import databeans.TagBean;
import databeans.UserBean;
import formbeans.LikeFlickrForm;

public class LikeFlickrAction extends Action {
	private Flickr flickr;
	private Token flickrToken;
	private FormBeanFactory<LikeFlickrForm> likeFormFactory = FormBeanFactory
			.getInstance(LikeFlickrForm.class);
	private UserDAO userDAO;
	private TagDAO tagDAO;
	private LikeDAO likeDAO;
	
	public LikeFlickrAction(Model model) {
		flickr = model.getFlickr();
		userDAO = model.getUserDAO();
		tagDAO = model.getTagDAO();
		likeDAO = model.getLikeDAO();
	}


	public String getName() { return "likeFlickr.do"; }

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		String fToken = user.getFlickrToken();
		String fSecret = user.getFlickrSecret();
		flickrToken = new Token(fToken, fSecret);
		LikeFlickrForm form = null;
		boolean isFav = false;
		String flickrId;
		String flickrTag;

		try {
			form = likeFormFactory.create(request);
		} catch (FormBeanException e) {
			e.printStackTrace();
		}
		flickrId = form.getId();
		
		try {
			isFav = flickr.isFavorite(flickrId, flickrToken);
		} catch (XPathExpressionException | IOException
				| ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		if (isFav) {
			flickr.removeFavourites(flickrToken, flickrId);
		}
		else {
			flickr.addFavourites(flickrToken, flickrId);
			String tag = "Yahaa";
			try {
				tag = flickr.getFlickrTag(flickrId, flickrToken);
			} catch (XPathExpressionException | IOException
					| ParserConfigurationException | SAXException e) {
				e.printStackTrace();
			}
			
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
			int count = 0;
			try {
				count = flickr.getFavoriteTotal(flickrId, flickrToken);
			} catch (XPathExpressionException | IOException
					| ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String curTime = flickr.getTime();
			LikeBean likeBean = new LikeBean();
			likeBean.setUserId(twitterId);
			likeBean.setTimestamp(curTime);
			likeBean.setContentId(flickrId);
			likeBean.setCount(count);
			likeDAO.create(likeBean);
		}
					
		return "home.do";
	}
}

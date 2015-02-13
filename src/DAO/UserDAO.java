package DAO;

/**
 *  Dian Wen
 *  2/12/2015
 * 
 *  Interfaces :
 *  
 *  readByTwitterId(String twitterId);
 *  readByFlickrId(String flickrId);
 *  readByUserId(int userId);
 *  
 *  
 *  createByTwitter(String twitterId);
 *  createByFlickr(String flickrId);
 *  create();
 *  
 *  
 *  getLikeGiven(int userId);
 *  getLikeReceived(int userId);
 *  
 *  
 *  hitLike(UserBean user);
 *  setTotalLikeReceived(UserBean user);
 *  
 */

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.UserBean;

public class UserDAO extends GenericDAO<UserBean> {
	public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(UserBean.class, tableName, cp);
	}

	public UserBean readByTwitterId (String twitterId) throws DAOException {
		try {
			UserBean[] array = match(MatchArg.equals("twitterId", twitterId));
			if (array == null || array.length == 0) {
				return null;
			} else {
				return array[0];
			}
		} catch (RollbackException e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserBean readByFlickrId (String flickrId) throws DAOException {
		try {
			UserBean[] array = match(MatchArg.equals("flickrId", flickrId));
			if (array == null || array.length == 0) {
				return null;
			} else {
				return array[0];
			}
		} catch (RollbackException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public UserBean readByUserId (String userId) throws DAOException {
		try {
			UserBean[] array = match(MatchArg.equals("id", userId));
			if (array == null || array.length == 0) {
				return null;
			} else {
				return array[0];
			}
		} catch (RollbackException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void create (UserBean user) {
		try {
			Transaction.begin();
			createAutoIncrement(user);
			Transaction.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
	
	public void createByTwitter(String twitterId) throws RollbackException, DAOException {
		try {
			Transaction.begin();
			if (readByFlickrId(twitterId) != null) {
				throw new RollbackException("Twitter account already exists");
			}
			UserBean user = new UserBean();
			user.setTwitterId(twitterId);
			create(user);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
	
	public void createByFlickr(String flickrId) throws RollbackException, DAOException {
		try {
			Transaction.begin();
			if (readByFlickrId(flickrId) != null) {
				throw new RollbackException("Flickr account already exists");
			}
			UserBean user = new UserBean();
			user.setFlickrId(flickrId);
			create(user);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
	
	public int getLikeGiven (int userId) throws DAOException {
		try {
			UserBean[] array = match(MatchArg.equals("id", userId));
			if (array == null || array.length == 0) {
				return -1;
			} else {
				return array[0].getLikeGiven();
			}
		} catch (RollbackException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int getLikeReceived (int userId) throws DAOException {
		try {
			UserBean[] array = match(MatchArg.equals("id", userId));
			if (array == null || array.length == 0) {
				return -1;
			} else {
				return array[0].getLikeReceived();
			}
		} catch (RollbackException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public void hitLike (UserBean user) throws RollbackException {
		try {
			Transaction.begin();
			user.setLikeGiven(user.getLikeGiven() + 1);
			update(user);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
	
	public void setTotalLikeReceived (UserBean user, int totalLikeReceived) throws RollbackException {
		try {
			Transaction.begin();
			user.setLikeReceived(totalLikeReceived);
			update(user);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
}

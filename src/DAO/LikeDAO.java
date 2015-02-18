package DAO;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import java.util.*;

import databeans.LikeBean;

public class LikeDAO extends GenericDAO<LikeBean> {
	public LikeDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(LikeBean.class, tableName, cp);
	}
	
	public void create (LikeBean like) {
		try {
			Transaction.begin();
			createAutoIncrement(like);
			Transaction.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
	
	public ArrayList<String> getTimeListByUserId(String userId) {
		try {
			LikeBean[] array = match(MatchArg.equals("userId", userId));
			if (array == null || array.length == 0) {
				throw new RollbackException("No Such User");
			} else {
				ArrayList<String> timeCollection = new ArrayList<String>();
				for (LikeBean item : array) {
					timeCollection.add(item.getTimestamp());
				}
				return timeCollection;
			}
		} catch (RollbackException e) {
			e.printStackTrace();
			return null;
		}
	} 
}

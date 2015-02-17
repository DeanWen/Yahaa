package DAO;

import java.util.ArrayList;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.LikeBean;
import databeans.TagBean;

public class TagDAO extends GenericDAO<TagBean> {
	public TagDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(TagBean.class, tableName, cp);
	}
	
	public void create (TagBean item) {
		try {
			Transaction.begin();
			createAutoIncrement(item);
			Transaction.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
	
	public TagBean readById(String userId, String tag) {
		try {
			TagBean[] array = match(MatchArg.and(MatchArg.equals("tag", tag), MatchArg.equals("userId", userId)));
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
	
	public int getCount(String userId, String tag) {
		try {
			TagBean[] array = match(MatchArg.and(MatchArg.equals("tag", tag), MatchArg.equals("userId", userId)));
			if (array == null || array.length == 0) {
				return 0;
			} else {
				return array[0].getCount();
			}
		} catch (RollbackException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public TagBean[] getAll() {
		try {
			TagBean[] array = match();
			if (array == null || array.length == 0) {
				return null;
			} else {
				return array;
			}
		} catch (RollbackException e) {
			e.printStackTrace();
			return null;
		}
	}
}

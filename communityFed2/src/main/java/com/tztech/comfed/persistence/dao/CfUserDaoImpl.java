package com.tztech.comfed.persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
import org.hibernate.criterion.Restrictions;

import com.tztech.comfed.persistence.DaoException;
import com.tztech.comfed.persistence.constants.ICfUser;
import com.tztech.comfed.persistence.entity.*;
import com.tztech.comfed.persistence.HibernateDaoImpl;

public class CfUserDaoImpl extends HibernateDaoImpl implements CfUserDao{

	public List queryByExample(Object userObj) throws DaoException {
	   return super.queryByExample(CfUser.class, userObj);
	}
	  
	public Object load(Long id) throws DaoException {
	    return super.load(CfUser.class, id);
	}
	  
	public List getAllUsers() throws DaoException {
	    return super.loadAll(CfUser.class);
	  }

	@Override
	public List findByEmail(String email) throws DaoException {
		List cs = new ArrayList();
	    cs.add(Restrictions.eq(ICfUser.EMAIL, email));
	    return super.findByCriterions(CfUser.class, cs);
	}

	@Override
	public Serializable insertUniqueUser(CfUser user) throws DaoException {
		// TODO Auto-generated method stub
		List users = findByEmail(user.getEmail());
		if (users.size() > 0)
		{
			throw new DaoException();
		}
		return save(user);
	}
}

package com.tztech.comfed.persistence.dao;

import java.io.Serializable;
import java.util.List;

import com.tztech.comfed.persistence.DaoException;
import com.tztech.comfed.persistence.entity.CfUser;



public interface CfUserDao extends BaseDao{
	
	public List getAllUsers() throws DaoException;
	public List findByEmail(String email) throws DaoException;
	public Serializable insertUniqueUser(CfUser user) throws DaoException;
	
}

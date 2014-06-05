package com.tztech.comfed.persistence.dao;

import java.io.Serializable;
import java.util.List;

import com.tztech.comfed.persistence.DaoException;

public interface BaseDao {
	
	public Serializable save(Object persistentObject) throws DaoException;
	public void delete(Object persistentObject) throws DaoException;
	public List queryByExample(Object persistentObject) throws DaoException;
	public Object load(Long id) throws DaoException;
	

}

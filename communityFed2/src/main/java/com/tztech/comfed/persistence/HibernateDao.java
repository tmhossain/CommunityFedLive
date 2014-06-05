package com.tztech.comfed.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

public interface HibernateDao  extends Dao{
	
	public static final int MAX_RESULT_THRESHOLD = 100;

	  public SessionFactory getSessionFactory();

	 // public void setSessionFactory(SessionFactory sessions);

	  //public Session setSession(Session session);

	  public Session getSession(boolean openSession);
	  
	  public Session getSession();

	  public void update(Object persistentObject) throws DaoException;

	  public void delete(Object persistentObject) throws DaoException;
	  
	  public List queryByExample(Class clazz, Object persistentObject) throws DaoException;
	  
	  public List queryByExample(Object object) throws DaoException;
	  
	  public List findAll(Class clazz) throws DaoException;

	  public List findByProperty(Class clazz, Criterion restriction) throws DaoException;

	  public List findByCriterions(Class clazz, List restrictions) throws DaoException;

	  public List findByCriterions(Class clazz, List restrictions, List orderBy) throws DaoException;

	  public List findBySqlQuery(String sqlQuery, String aliasName, Class clazz) throws DaoException;
	  
	  public List findBySqlQuery(String sql) throws DaoException;

	  public List findByHqlQuery(String hqlQuery) throws DaoException;

	  public List findByHqlQuery(String queryString, String paramName, Object value) throws DaoException;

	  public List findByHqlQuery(String queryString, String paramName, Object value, int firstResult, int maxResult) throws DaoException;

	  public List findByHqlQuery(Query query) throws DaoException;

	  public List findByHqlQuery(final String queryString, final String[] paramNames, final Object[] values) throws DaoException;

	  public List findByHqlQuery(final String queryString, final String[] paramNames, final Object[] values, final int firstResult, final int maxResult) throws DaoException;

	  public List findByValueBean(final String queryString, final Object valueBean) throws DaoException;

	  public List findByNamedQuery(String queryName) throws DaoException;

	  public List findByNamedQuery(String queryName, Object value) throws DaoException;

	  public List findByNamedQuery(final String queryName, final Object[] values) throws DaoException;

	  public abstract List findByNamedQueryAndNamedParam(String queryName, String paramName, Object value) throws DaoException;

	  public List findByNamedQueryAndNamedParam(final String queryName, final String[] paramNames, final Object[] values) throws DaoException;

	  public List findByNamedQueryAndValueBean(final String queryName, final Object valueBean) throws DaoException;

	  public boolean isCacheQueries();

	  public String getQueryCacheRegion();

	  public Serializable save(Object persistentObject) throws DaoException;	  	  

	  public void saveOrUpdate(Object persistentObject) throws DaoException;

	  public List find(String queryString, int firstResult, int maxResult) throws DaoException;

	  public List find(String queryString) throws DaoException;

	  public List find(String queryString, Object value) throws DaoException;

	  public List find(final String queryString, final Object[] values) throws DaoException;

	  public List find(final String queryString, final Object[] values, final int firstResult, final int maxResult) throws DaoException;
	  
	  public Object load(Class clazz, Long id) throws DaoException;

	  public List loadAll(final Class entityClass) throws DaoException;

	  public List loadAll(final Class entityClass, final int firstResult, final int maxResult) throws DaoException;

}

package com.tztech.comfed.persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tztech.comfed.form.SignUpForm;
import com.tztech.comfed.persistence.entity.CfUser;
import com.tztech.comfed.util.TransactionController;

@Repository
public class HibernateDaoImpl implements HibernateDao {

	private boolean autoCommit = true;

	private boolean autoCloseSession = false;

	private boolean cacheQueries = false;

	private String queryCacheRegion;

	protected static Logger logger = Logger.getLogger(HibernateDaoImpl.class);

	/**
	 * Empty constructor
	 */
	// protected HibernateDaoImpl() {
	public HibernateDaoImpl() {
		super();
	}

	@Autowired
	private TransactionController controller;

	/**
	 * Constructor with specified session factory
	 * 
	 * @param sessionFactory
	 */

	// public HibernateDaoImpl(SessionFactory sessionFactory) {
	// setSessionFactory(sessionFactory);
	// }

	public SessionFactory getSessionFactory() {
		return controller.getSessionFactory();
	}

	//
	// public void setSessionFactory(SessionFactory sessions) {
	//
	// }

	// public Session setSession(Session session) {
	// return HibernateSessionManager.setSession(session);
	// }

	public TransactionController getController() {
		return controller;
	}

	public void setController(TransactionController controller) {
		this.controller = controller;
	}

	public Session getSession(boolean openSession) {
		return controller.getSession();
	}

	public Session getSession() {
		return getSession(false);
	}

	public void closeSession(boolean localTransaction) throws DaoException {
		if (localTransaction)
			controller.closeSession();
	}

	public void flushSession() throws DaoException {
		if (getSession() != null)
			try {
				getSession().flush();
			} catch (HibernateException e) {
				logger.error("Fail to flush session", e);
				throw new DaoException("Fail to flush session", e);
			}
	}

	public void clearSession() throws DaoException {
		if (getSession() != null)
			try {
				getSession().clear();
			} catch (HibernateException ex) {
				logger.error("Fail to clear session", ex);
				throw new DaoException("Fail to clear session", ex);
			}
	}

	public void beginTransaction(boolean localTransaction) throws DaoException {

		controller.beginTransaction();
	}

//	public void commitTransaction(boolean localTransaction) throws DaoException {
//		if (localTransaction)
//			controller.commit();
//	}

	public void rollbackTransaction(boolean localTransaction)
			throws DaoException {
		if (localTransaction)
			controller.rollbackTransaction();
	}

	public boolean isAutoCommit() {
		return this.autoCommit;
	}

	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}

	public boolean isAutoCloseSession() {
		return this.autoCloseSession;
	}

	public void setAutoCloseSession(boolean autoClose) {
		this.autoCloseSession = autoClose;
	}

	public void update(Object persistentObject) throws DaoException {
		boolean localTransaction = false;
		try {
			controller.beginTransaction();
			Session session = controller.getSession();
			///beginTransaction(localTransaction);
			session.update(persistentObject);
			if (autoCommit)
				//commitTransaction(localTransaction);
				controller.commit();
			//session.flush();
			//session.refresh(persistentObject);
		} catch (HibernateException ex) {
			controller.rollbackTransaction();
			//rollbackTransaction(localTransaction);
			logger.error("Fail to update object: " + persistentObject);
			throw new DaoException("Fail to update", ex);
		} finally {
			if (autoCloseSession)
				closeSession(localTransaction);
		}
	}

	public void delete(Object persistentObject) throws DaoException {
		boolean localTransaction = false;
		try {
			controller.beginTransaction();
			controller.delete(persistentObject);
			controller.commit();
		} catch (HibernateException ex) {
			controller.rollbackTransaction();
			//rollbackTransaction(localTransaction);
			logger.error("Fail to delete object: " + persistentObject);
			throw new DaoException("Fail tlo delete", ex);
		} finally {
			if (autoCloseSession)
				closeSession(localTransaction);
		}
	}

	public List queryByExample(Class clazz, Object persistentObject)
			throws DaoException {
		List objs = new ArrayList();

		boolean localTransaction = false;
		try {
			controller.beginTransaction();
			Session session = controller.getSession();
			//beginTransaction(localTransaction);
			objs = session.createCriteria(clazz)
					.add(Example.create(persistentObject)).list();
			if (autoCommit)
				//commitTransaction(localTransaction);
				controller.commit();
		} catch (HibernateException ex) {
			//rollbackTransaction(localTransaction);
			controller.rollbackTransaction();
			logger.error("Fail to find all  objects", ex);
			throw new DaoException("Fail to find all  objects", ex);
		} finally {
			if (autoCloseSession)
				closeSession(localTransaction);
		}
		return objs;
	}

	public List findAll(Class clazz) throws DaoException {
		List objs = new ArrayList();

		boolean localTransaction = false;
		try {
			controller.beginTransaction();
			Session session = controller.getSession();
			//beginTransaction(localTransaction);
			objs = session.createCriteria(clazz).list();
			if (autoCommit)
				controller.commit();
				//commitTransaction(localTransaction);
		} catch (HibernateException ex) {
			controller.rollbackTransaction();
			//rollbackTransaction(localTransaction);
			logger.error("Fail to find all  objects", ex);
			throw new DaoException("Fail to find all  objects", ex);
		} finally {
			if (autoCloseSession)
				closeSession(localTransaction);
		}
		return objs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#update(Class, Criterion)
	 */
	public List findByProperty(Class clazz, Criterion restriction)
			throws DaoException {
		List objs = new ArrayList();

		boolean localTransaction = false;
		try {
			controller.beginTransaction();
			Session session = controller.getSession();
			//beginTransaction(localTransaction);
			objs = session.createCriteria(clazz).add(restriction).list();
			if (autoCommit)
				controller.commit();
		} catch (HibernateException ex) {
			rollbackTransaction(localTransaction);
			logger.error("Fail to find objects by property", ex);
			throw new DaoException("Fail to find objects by property", ex);
		} finally {
			if (autoCloseSession)
				closeSession(localTransaction);
		}
		return objs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findByCriterions(Class, List)
	 */
	public List findByCriterions(Class clazz, List restrictions)
			throws DaoException {
		return findByCriterions(clazz, restrictions, null);
	}

	public List findByCriterions(Class clazz, List restrictions, List orderBy)
			throws DaoException {
		List objs = new ArrayList();

		//boolean localTransaction = false;
		try {
			
			controller.beginTransaction();

			Criteria criteria = controller.getSession().createCriteria(clazz);
			Iterator it = restrictions.iterator();

			while (it.hasNext()) {
				Criterion criterion = (Criterion) it.next();
				criteria.add(criterion);
			}

			if (orderBy != null) {
				it = orderBy.iterator();

				while (it.hasNext()) {
					criteria.addOrder((Order) it.next());
				}
			}

			objs = criteria.list();
			if (autoCommit)
				controller.commit();
				//commitTransaction(localTransaction);
		} catch (HibernateException ex) {
			//rollbackTransaction(localTransaction);
			controller.rollbackTransaction();
			logger.error("Fail to find objects by criterions", ex);
			throw new DaoException("Fail to find objects by criterions", ex);
		} finally {
			if (autoCloseSession)
				controller.closeSession();
				//closeSession(localTransaction);
		}

		return objs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findByCriterions(Class, List,int,int)
	 */
	public List findByCriterions(Class clazz, List restrictions,
			int firstResult, int maxResult) throws DaoException {
		List objs = new ArrayList();

		//boolean localTransaction = false;
		try {
			controller.beginTransaction();
			Session session = controller.getSession();
			//beginTransaction(localTransaction);
			Criteria criteria = session.createCriteria(clazz)
					.setFirstResult(firstResult).setMaxResults(maxResult);
			Iterator it = restrictions.iterator();
			while (it.hasNext())
				criteria.add((Criterion) it.next());
			objs = criteria.list();
			if (autoCommit)
				controller.commit();
				//commitTransaction(localTransaction);
		} catch (HibernateException ex) {
			controller.rollbackTransaction();
			//rollbackTransaction(localTransaction);
			logger.error("Fail to find objects by criterions", ex);
			throw new DaoException("Fail to find objects by criterions", ex);
		} finally {
			if (autoCloseSession) controller.closeSession();
		}
				
		return objs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findBySqlQuery(String, String, Class)
	 */
	public List findBySqlQuery(String sql, String aliasName, Class clazz)
			throws DaoException {
		List result = new ArrayList();

		//boolean localTransaction = false;
		try {
			controller.beginTransaction();
			Session session = controller.getSession();
			//beginTransaction(localTransaction);
			result = session.createSQLQuery(sql).addEntity(aliasName, clazz)
					.list();
			if (autoCommit)
				controller.commit();
				//commitTransaction(localTransaction);
		} catch (HibernateException ex) {
			controller.rollbackTransaction();
			//rollbackTransaction(localTransaction);
			logger.error("Fail to execute query", ex);
			throw new DaoException("Fail to execute query", ex);
		} finally {
			if (autoCloseSession)
				controller.closeSession();
				//closeSession(localTransaction);
		}
		return result;
	}

	public List findBySqlQuery(String sql) throws DaoException {
		List result = new ArrayList();

		//boolean localTransaction = false;
		try {
			controller.beginTransaction();
			Session session = controller.getSession();
			//beginTransaction(localTransaction);
			result = session.createSQLQuery(sql).list();
			if (autoCommit)
				controller.commit();
				//commitTransaction(localTransaction);
		} catch (HibernateException ex) {
			controller.rollbackTransaction();
			//rollbackTransaction(localTransaction);
			logger.error("Fail to execute query", ex);
			throw new DaoException("Fail to execute query", ex);
		} finally {
			if (autoCloseSession)
				controller.closeSession();
				//closeSession(localTransaction);
		}
		return result;
	}

	public List findByHqlQuery(Query query) throws DaoException {
		List result = new ArrayList();

		//boolean localTransaction = false;
		try {
			controller.beginTransaction();
			//controller.getSession().
			//beginTransaction(localTransaction);
			result = query.list();
			if (autoCommit)
				controller.commit();
				//commitTransaction(localTransaction);
		} catch (HibernateException ex) {
			controller.rollbackTransaction();
			//rollbackTransaction(localTransaction);
			logger.error("Fail to execute query", ex);
			throw new DaoException("Fail to execute query", ex);
		} finally {
			if (autoCloseSession)
				controller.closeSession();
				//closeSession(localTransaction);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findByHqlQuery(String)
	 */
	public List findByHqlQuery(String hql) throws DaoException {
		List result = new ArrayList();

		boolean localTransaction = false;
		try {
			Session session = this.openSession();
			beginTransaction(localTransaction);
			result = session.createQuery(hql).list();
			if (autoCommit)
				controller.commit();
				//commitTransaction(localTransaction);
		} catch (HibernateException ex) {
			controller.rollbackTransaction();
			//rollbackTransaction(localTransaction);
			logger.error("Fail to execute query", ex);
			throw new DaoException("Fail to execute query", ex);
		} finally {
			if (autoCloseSession)
				closeSession(localTransaction);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findByHqlQuery(String, String,Object)
	 */
	public List findByHqlQuery(String hql, String paramName, Object value)
			throws DaoException {
		return findByHqlQuery(hql, new String[] { paramName },
				new Object[] { value });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findByHqlQuery(String, String, Object, int, int)
	 */
	public List findByHqlQuery(String hql, String paramName, Object value,
			int firstResult, int maxResult) throws DaoException {
		return findByHqlQuery(hql, new String[] { paramName },
				new Object[] { value }, firstResult, maxResult);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findByHqlQuery(String, String[], Object[])
	 */
	public List findByHqlQuery(final String hql, final String[] paramNames,
			final Object[] values) throws DaoException {

		try {
			if (paramNames.length != values.length) {
				throw new IllegalArgumentException(
						"Length of paramNames array must match length of values array");
			}
			Session session = this.openSession();
			Query query = session.createQuery(hql);
			prepareQuery(query);

			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					applyNamedParameterToQuery(query, paramNames[i], values[i]);
				}
			}

			List lst = query.list();
			if (lst != null) {
				for (int i = 0; i < lst.size(); i++) {
					session.evict(lst.get(i));
				}
			}
			return lst;

		} catch (HibernateException ex) {
			logger.error("Fail to find", ex);
			throw new DaoException("Fail to find", ex);
		} finally {
			if (autoCloseSession)
				closeSession(true);
		}
	}

	public List findByHqlQuery(final String hql, final String[] paramNames,
			final Object[] values, final int firstResult, final int maxResult)
			throws DaoException {

		if (paramNames.length != values.length) {
			throw new IllegalArgumentException(
					"Length of paramNames array must match length of values array");
		}
		try {
			Session session = this.openSession();
			Query queryObject = session.createQuery(hql);
			queryObject.setFirstResult(firstResult);
			queryObject.setMaxResults(maxResult);
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					applyNamedParameterToQuery(queryObject, paramNames[i],
							values[i]);
				}
			}
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by named param");
			throw new DaoException("Fail to find by named param", ex);
		} finally {
			if (autoCloseSession)
				closeSession(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findByValueBean(String, Object)
	 */
	public List findByValueBean(final String queryString, final Object valueBean)
			throws DaoException {

		Session session = this.openSession();
		try {
			Query queryObject = session.createQuery(queryString);
			prepareQuery(queryObject);
			queryObject.setProperties(valueBean);
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by valueBean", ex);
			throw new DaoException("Fail to find by valueBeam", ex);
		} finally {
			if (autoCloseSession)
				closeSession(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findByNamedQuery(String)
	 */
	public List findByNamedQuery(String queryName) throws DaoException {
		return findByNamedQuery(queryName, (Object[]) null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findByNamedQuery(String, Object)
	 */
	public List findByNamedQuery(String queryName, Object value)
			throws DaoException {
		return findByNamedQuery(queryName, new Object[] { value });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findByNamedQuery(String, Object[])
	 */
	public List findByNamedQuery(final String queryName, final Object[] values)
			throws DaoException {

		Session session = this.openSession();

		try {
			Query queryObject = session.getNamedQuery(queryName);
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by Named query", ex);
			throw new DaoException("Fail to find by Named query", ex);
		} finally {
			if (autoCloseSession)
				closeSession(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findByNamedQueryAndNamedParam(String, String, Object)
	 */
	public List findByNamedQueryAndNamedParam(String queryName,
			String paramName, Object value) throws DaoException {
		return findByNamedQueryAndNamedParam(queryName,
				new String[] { paramName }, new Object[] { value });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findByNamedQueryAndNamedParam(String, String[],
	 * Object[])
	 */
	public List findByNamedQueryAndNamedParam(final String queryName,
			final String[] paramNames, final Object[] values)
			throws DaoException {
		if (paramNames != null && values != null
				&& paramNames.length != values.length) {
			throw new IllegalArgumentException(
					"Length of paramNames array must match length of values array");
		}
		Session session = this.openSession();
		try {
			Query queryObject = session.getNamedQuery(queryName);
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					applyNamedParameterToQuery(queryObject, paramNames[i],
							values[i]);
				}
			}
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by Named query and Named Param", ex);
			throw new DaoException(
					"Fail to find by Named query and Named Param", ex);
		} finally {
			// if (autoCloseSession) closeSession(localTransaction);
			if (autoCloseSession)
				closeSession(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#findByNamedQueryAndValueBean(String, Object)
	 */
	public List findByNamedQueryAndValueBean(final String queryName,
			final Object valueBean) throws DaoException {
		controller.beginTransaction();
		Session session = controller.getSession();
		try {
			Query queryObject = session.getNamedQuery(queryName);
			prepareQuery(queryObject);
			queryObject.setProperties(valueBean);
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by Named query and value bean", ex);
			throw new DaoException(
					"Fail to find by Named query and value bean", ex);
		} finally {
			// if (autoCloseSession) closeSession(localTransaction);
			if (autoCloseSession)
				closeSession(true);
		}
	}

	/**
	 * Make query preparation, such as seting cacheable and cache region
	 * 
	 * @param queryObject
	 */
	protected void prepareQuery(Query queryObject) {
		if (isCacheQueries()) {
			queryObject.setCacheable(true);
			if (getQueryCacheRegion() != null) {
				queryObject.setCacheRegion(getQueryCacheRegion());
			}
		}
	}

	/**
	 * Apply parameter value to query parameter
	 * 
	 * @param queryObject
	 * @param paramName
	 * @param value
	 * @throws HibernateException
	 */
	protected void applyNamedParameterToQuery(Query queryObject,
			String paramName, Object value) throws HibernateException {
		if (value instanceof Collection) {
			queryObject.setParameterList(paramName, (Collection) value);
		} else if (value instanceof Object[]) {
			queryObject.setParameterList(paramName, (Object[]) value);
		} else if (value instanceof BigInteger) {
			queryObject.setBigInteger(paramName, (BigInteger) value);
		} else {
			queryObject.setParameter(paramName, value);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#isCacheQueries()
	 */
	public boolean isCacheQueries() {
		return cacheQueries;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#getQueryCacheRegion()
	 */
	public String getQueryCacheRegion() {
		return queryCacheRegion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#save(Object)
	 */
	public Serializable save(Object persistentObject) throws DaoException {
		boolean localTransaction = false;
		try {
			Session session = this.openSession();
			beginTransaction(localTransaction);
			Serializable id = session.save(persistentObject);
			if (autoCommit)
				controller.commit();
				//commitTransaction(localTransaction);
			//session.flush();
			//session.refresh(persistentObject);
			return id;
		} catch (HibernateException ex) {
			logger.error("Fail to save object: " + persistentObject);
			throw new DaoException("Fail to save persistentObject", ex);
		} catch (Exception e) {
			// logger.error("Fail to save persistentObject", e);
			throw new DaoException("Fail to save persistentObject", e);
		} finally {
			if (autoCloseSession)
				closeSession(localTransaction);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#saveOrUpdate(Object)
	 */
	public void saveOrUpdate(Object persistentObject) throws DaoException {
		boolean localTransaction = false;
		try {
			Session session = this.openSession();
			beginTransaction(localTransaction);
			session.saveOrUpdate(persistentObject);
			if (autoCommit)
				controller.commit();
				//commitTransaction(localTransaction);
			session.flush();
			session.refresh(persistentObject);
		} catch (HibernateException ex) {
			// logger.error("Fail to save or update persistentObject", ex);
			throw new DaoException("Fail to save or update persistentObject",
					ex);
		} finally {
			if (autoCloseSession)
				closeSession(localTransaction);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#find(String, int, int)
	 */
	public List find(String queryString, int firstResult, int maxResult)
			throws DaoException {
		return find(queryString, (Object[]) null, firstResult, maxResult);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#find(String)
	 */
	public List find(String queryString) throws DaoException {
		return find(queryString, (Object[]) null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#find(String, Object)
	 */
	public List find(String queryString, Object value) throws DaoException {
		return find(queryString, new Object[] { value });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#find(String, Object[])
	 */
	public List find(final String queryString, final Object[] values)
			throws DaoException {
		boolean localTransaction = false;
		Session session = this.openSession();
		try {
			Query queryObject = session.createQuery(queryString);
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by query string", ex);
			throw new DaoException("Fail to find by query string", ex);
		} finally {
			if (autoCloseSession)
				closeSession(localTransaction);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#find(String, Object[], int, int)
	 */
	public List find(final String queryString, final Object[] values,
			final int firstResult, final int maxResult) throws DaoException {
		Session session = this.openSession();
		try {
			Query queryObject = session.createQuery(queryString)
					.setFirstResult(firstResult).setMaxResults(maxResult);
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.list();
		} catch (HibernateException ex) {
			logger.error("Fail to find by query string", ex);
			throw new DaoException("Fail to find by query string", ex);
		} finally {
			// if (autoCloseSession) closeSession(localTransaction);
			if (autoCloseSession)
				closeSession(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#loadAll(Class)
	 */
	public List loadAll(final Class entityClass) throws DaoException {
		boolean localTransaction = false;
		Session session = this.openSession();
		try {
			beginTransaction(localTransaction);
			Criteria criteria = session.createCriteria(entityClass);
			if (autoCommit)
				controller.commit();
				//commitTransaction(localTransaction);
			return criteria.list();
		} catch (HibernateException ex) {
			logger.error("Fail to load all ", ex);
			throw new DaoException("Fail to load all", ex);
		} finally {
			if (autoCloseSession)
				closeSession(localTransaction);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see HibernateDao#loadAll(Class, int, int)
	 */
	public List loadAll(final Class entityClass, final int firstResult,
			final int maxResult) throws DaoException {
		boolean localTransaction = false;
		Session session = this.openSession();
		try {
			beginTransaction(localTransaction);
			Criteria criteria = session.createCriteria(entityClass)
					.setFirstResult(firstResult).setMaxResults(maxResult);
			if (autoCommit)
				controller.commit();
				//commitTransaction(localTransaction);
			return criteria.list();
		} catch (HibernateException ex) {
			logger.error("Fail to load all ", ex);
			throw new DaoException("Fail to load all", ex);
		} finally {
			if (autoCloseSession)
				closeSession(localTransaction);
		}
	}

	/*
	 * Get session associated with current thread. Open new session if not
	 * exist.
	 * 
	 * @return session
	 */
	protected Session openSession() {
		return getSession(true);
	}

	public Object load(Class clazz, Long id) throws DaoException {

		List cs = new ArrayList();
		cs.add(Restrictions.eq("id", id));
		List list = findByCriterions(clazz, cs);
		return (((list != null) && (list.size() > 0)) ? list.get(0) : null);
	}

	@Override
	public List queryByExample(Object object) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public CfUser getUserByUserName(String userName){
		CfUser user = null ;
		List cs = new ArrayList();
		cs.add(Restrictions.eq("userName", userName));
		List list = findByCriterions(CfUser.class, cs);
		if(list.size() > 0){
			user = (CfUser)list.get(0);
		}
		return user;
	}
	/**
	 * Adds a user in db
	 * @param form
	 * @return
	 * @throws DaoException
	 */
	public Long addUser(SignUpForm form) throws DaoException{
		//initialize the session
		controller.beginTransaction();
		CfUser user = new CfUser();
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		user.setEmail(form.getEmail());
		user.setInsertedAt(new Date());
		user.setUpdatedAt(new Date());
		user.setUserName(form.getUserName());
		user.setPassword(form.getPassword());
		Long id = (Long)controller.getSession().save(user);
		controller.commit();
		return id;
		
	}

}
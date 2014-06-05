package com.tztech.comfed.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



/**
 * @author A4MT9ZZ
 *
 */
@Repository
public class TransactionController {

	@Autowired 
	SessionFactory sessionFactory ;

	
	private Session session;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void beginTransaction() {
		if(session == null || !session.isOpen()){
			session = sessionFactory.openSession();
			session.getTransaction().begin();
		}
	}

	public void commit(boolean shouldClose) {
		session.flush();
		
		if(shouldClose && session.isOpen()){
			session.close();	
		}
	}
	/**
	 * Commits and closes session
	 */
	public void commit() {
		this.session.getTransaction().commit();
		this.session.close();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
//
//	public void update(User entity) {
//		this.session.save(entity);
//	}

	public void rollbackTransaction() {
		// TODO Auto-generated method stub
		session.getTransaction().rollback();
	}

	public void closeSession() {
		session.close();
		
	}

	public void delete(Object persistentObject) {
		session.delete(persistentObject);
		
	}
	
	
	
	
}


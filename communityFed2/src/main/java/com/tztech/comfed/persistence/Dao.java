package com.tztech.comfed.persistence;


/**
 * RpcInterface that defines common methods used by data access.
 * 
 */
public interface Dao {
  
  /**
   * Begin transaction
   */
  public void beginTransaction(boolean localTransaction) throws DaoException;

  /**
   * Begin transaction
   * 
   */
  //public void commitTransaction(boolean localTransaction) throws DaoException;

  /**
   * Rollback transaction
   */
  public void rollbackTransaction(boolean localTransaction) throws DaoException;

  /**
   * Check if auto commit is set
   * 
   * @return Auto commit flag
   */
  public boolean isAutoCommit();

  /**
   * Set auto commit flag
   * 
   * @param autoCommit
   */
  public void setAutoCommit(boolean autoCommit);

  /**
   * Check if auto close session is set
   * 
   * @return Auto close session flag
   */
  public boolean isAutoCloseSession();

  /**
   * Set auto close session flag
   * 
   * @param autoClose
   */
  public void setAutoCloseSession(boolean autoClose);

  /**
   * Close session
   * 
   */
  public void closeSession(boolean localTransaction) throws DaoException;

  /**
   * Flush session
   * 
   * @return
   */
  public void flushSession() throws DaoException;

  /**
   * Clear session
   * 
   */
  public void clearSession() throws DaoException;
}
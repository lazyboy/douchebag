/**
 * Copyright 2010 lazyboybd@gmail.com, Debasis Roy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.douchebag.server.hibernate;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

/**
 *
 * @author debasis
 */
public class HibernateHelper {

  private Session mSession = null;
  private Transaction mTransaction = null;

  public Session getSession() {
    return mSession;
  }

  public HibernateHelper() throws Exception {
    try {
      mSession = HibernateUtil.getSession();
      mSession.setCacheMode(CacheMode.IGNORE);

    }
    catch (Exception e) {
      throw e;
    }
  }

  public Object load(Class theClazz, java.io.Serializable id) throws Exception {
    try {
      return mSession.load(theClazz, id);
    }
    catch (Exception e) {
      throw e;
    }
  }

  public Object get(Class theClazz, java.io.Serializable id) throws Exception {
    try {
      return mSession.get(theClazz, id);
    }
    catch (Exception e) {
      throw e;
    }
  }

  public Query createQuery(String queryString) throws Exception {
    try {
      return mSession.createQuery(queryString);
    }
    catch (Exception e) {
      throw e;
    }
  }

  public java.util.List list(Query query) throws Exception {
    try {
      return query.list();
    }
    catch (Exception e) {
      throw e;
    }
  }

  public void save(Object o) throws Exception {
    try {
      mTransaction = mSession.beginTransaction();
      mSession.save(o);
      mTransaction.commit();
    }
    catch (Exception e) {
      if (mTransaction != null) {
        // In case of an exception, roll back the transaction.
        try {
          mTransaction.rollback();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      throw e;
    }
  }

  public void update(Object o) throws Exception {
    try {
      mTransaction = mSession.beginTransaction();
      mSession.update(o);
      mTransaction.commit();
    }
    catch (Exception e) {
      if (mTransaction != null) {
        // In case of an exception, roll back the transaction.
        try {
          mTransaction.rollback();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      throw e;
    }
  }

  public void delete(Object o) throws Exception {
    try {
      mTransaction = mSession.beginTransaction();
      mSession.delete(o);
      mTransaction.commit();
    }
    catch (Exception e) {
      if (mTransaction != null) {
        // In case of an exception, roll back the transaction.
        try {
          mTransaction.rollback();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      throw e;
    }
  }

  public void saveOrUpdate(Object o) throws Exception {
    try {
      mTransaction = mSession.beginTransaction();
      mSession.saveOrUpdate(o);
      mTransaction.commit();
    }
    catch (Exception e) {
      if (mTransaction != null) {
        // In case of an exception, roll back the transaction.
        try {
          mTransaction.rollback();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      throw e;
    }
  }

  public void merge(Object o) throws Exception {
    try {
      mTransaction = mSession.beginTransaction();
      mSession.merge(o);
      mTransaction.commit();
    }
    catch (Exception e) {
      if (mTransaction != null) {
        // In case of an exception, roll back the transaction.
        try {
          mTransaction.rollback();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      throw e;
    }
  }

  /**
   * Close the database connection.
   */
  public void close() throws Exception {
    if (mSession != null) {
      try {
        // And always close the connection.
        mSession.close();
      }
      catch (Exception ex) {
        throw ex;
      }
    }
  }
}

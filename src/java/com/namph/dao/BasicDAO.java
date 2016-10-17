/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author linhlh2
 * @param <T>
 */
@Repository
public abstract class BasicDAO<T> extends HibernateDaoSupport{

    /**
     * constructor
     */
    protected BasicDAO() {
    }

    protected void initialize(final Object proxy) throws DataAccessException {
        getHibernateTemplate().initialize(proxy);
    }

    protected T merge(T entity) throws DataAccessException {
        return (T) getHibernateTemplate().merge(entity);
    }

    protected void persist(T entity) throws DataAccessException {
        getHibernateTemplate().persist(entity);
    }

    public void refresh(T entity) throws DataAccessException {
        getHibernateTemplate().refresh(entity);
    }

    public void save(T entity) throws DataAccessException {
        getHibernateTemplate().save(entity);
    }

    public void saveOrUpdate(T entity) throws DataAccessException {
//        entity = getHibernateTemplate().merge(entity);
        getHibernateTemplate().saveOrUpdate(entity);
    }


    public void saveOrUpdateAll(Collection<T> entities) throws
            DataAccessException {
        for (T entity : entities) {
            saveOrUpdate(entity);
        }
    }

    public void update(T entity) throws DataAccessException {
        getHibernateTemplate().update(entity);
    }

    public void delete(T entity) throws DataAccessException {
        getHibernateTemplate().delete(entity);
    }

    protected void deleteAll(Collection<T> entities) throws DataAccessException {
        getHibernateTemplate().deleteAll(entities);
    }

    protected T get(Class<T> entityClass, Serializable id) throws DataAccessException {
        return (T) getHibernateTemplate().get(entityClass, id);
    }

    public void rollback() throws DataAccessException{
        Session session = getSession();

        Transaction tx = session.getTransaction();

        try {
            if (tx != null && !tx.wasCommitted()) {
                tx.rollback();
            }
        }finally{
            session.close();
        }
    }

    public Date getSysDate() throws DataAccessException {
        String sql = "select sysdate from dual";

        SQLQuery q = getSession().createSQLQuery(sql);

        q.addScalar("sysdate", Hibernate.TIMESTAMP);

        return (Date) q.uniqueResult();
    }
}

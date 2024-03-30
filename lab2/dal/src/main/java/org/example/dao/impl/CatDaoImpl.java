package org.example.dao.impl;

import org.example.dao.CatDao;
import org.example.hibernate.HibernateSessionFactoryUtil;
import org.example.model.Cat;
import org.example.model.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CatDaoImpl implements CatDao {

    @Override
    public void create(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(cat);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(cat);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.remove(session.contains(cat) ? cat : session.merge(cat));
        tx.commit();
        session.close();
    }

    @Override
    public Cat getByName(String name) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().
                createQuery("FROM Cat WHERE name = :name", Cat.class).
                setParameter("name", name).
                uniqueResult();
    }

    @Override
    public Cat getById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Cat.class, id);
    }

    @Override
    public List<Cat> getAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().
                createQuery("FROM Cat", Cat.class).
                getResultList();
    }

}

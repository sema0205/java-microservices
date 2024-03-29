package org.example.dao.impl;

import org.example.dao.OwnerDao;
import org.example.hibernate.HibernateSessionFactoryUtil;
import org.example.model.Cat;
import org.example.model.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OwnerDaoImpl implements OwnerDao {

    @Override
    public void create(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(owner);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(owner);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.remove(session.contains(owner) ? owner : session.merge(owner));
        tx.commit();
        session.close();
    }

    @Override
    public Owner getByName(String name) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().
                createQuery("FROM Owner WHERE name = :name", Owner.class).
                setParameter("name", name).
                uniqueResult();
    }

    @Override
    public Owner getById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Owner.class, id);
    }

    @Override
    public List<Owner> getAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().
                createQuery("FROM Owner", Owner.class).
                getResultList();
    }

}

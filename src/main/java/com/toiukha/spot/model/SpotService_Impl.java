package com.toiukha.spot.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.toiukha.spot.util.HibernateUtil;

public class SpotService_Impl implements SpotService_interface {

    @Override
    public List<SpotVO> getAllSpots() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("FROM SpotVO", SpotVO.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public void addSpot(SpotVO spotVO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(spotVO);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("新增失敗: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void updateSpot(SpotVO spotVO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(spotVO);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("更新失敗: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteSpot(Integer spotId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SpotVO spot = session.get(SpotVO.class, spotId);
            if (spot != null) session.remove(spot);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("刪除失敗: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    
    @Override
    public SpotVO getOneSpot(Integer spotId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(SpotVO.class, spotId);
        } finally {
            session.close();
        }
    }
    
}

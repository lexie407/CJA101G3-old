package com.toiukha.spot.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.toiukha.spot.util.HibernateUtil;

public class SpotDAO_Impl implements SpotDAO_interface {

	@Override
	public void insert(SpotVO spotVO) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(spotVO); // Hibernate 6.x 推薦用 persist
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("新增景點失敗: " + e.getMessage());
		}
	}

	@Override
	public void update(SpotVO spotVO) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(spotVO); // Hibernate 6.x 推薦用 merge
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("更新景點失敗: " + e.getMessage());
		}
	}

	@Override
	public void delete(Integer spotId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			SpotVO spotVO = session.get(SpotVO.class, spotId);
			if (spotVO != null) {
				session.remove(spotVO); // Hibernate 6.x 推薦用 remove
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("刪除景點失敗: " + e.getMessage());
		}
	}

	@Override
	public SpotVO findById(Integer spotId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(SpotVO.class, spotId);
		} catch (Exception e) {
			throw new RuntimeException("查詢景點失敗: " + e.getMessage());
		}
	}

	@Override
    public List<SpotVO> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<SpotVO> query = session.createQuery("FROM SpotVO", SpotVO.class);
            List<SpotVO> result = query.getResultList();
            System.out.println("【DAO】查詢到 " + result.size() +" 筆景點資料");
            return result;
        } catch (Exception e) {
            System.err.println("【DAO 錯誤】" + e.getMessage());
            throw new RuntimeException("查詢失敗：" + e.getMessage());
        }
    }
}

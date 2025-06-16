package com.toiukha.spot.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.toiukha.hibernate.HibernateUtil;

public class SpotDAO_Impl implements SpotDAO_interface {

	@Override
	public void insert(SpotVO spotVO) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			// 調試信息
			System.out.println("[DAO DEBUG] 準備插入景點：" + spotVO.toString());
			
			// 確保必要欄位不為空
			if (spotVO.getSpotName() == null || spotVO.getSpotName().trim().isEmpty()) {
				throw new RuntimeException("景點名稱不能為空");
			}
			if (spotVO.getCrtId() == null) {
				throw new RuntimeException("建立者ID不能為空");
			}
			if (spotVO.getSpotLoc() == null || spotVO.getSpotLoc().trim().isEmpty()) {
				throw new RuntimeException("景點地址不能為空");
			}
			if (spotVO.getSpotStatus() == null) {
				spotVO.setSpotStatus((byte) 0); // 確保有預設值
			}
			
			System.out.println("[DAO DEBUG] 驗證通過，開始持久化");
			session.persist(spotVO); // Hibernate 6.x 推薦用 persist
			transaction.commit();
			System.out.println("[DAO DEBUG] 景點插入成功，ID：" + spotVO.getSpotId());
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("[DAO ERROR] 新增景點失敗：" + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("新增景點失敗: " + e.getMessage(), e);
		}
	}

	@Override
	public void update(SpotVO spotVO) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			// 調試信息
			System.out.println("[DAO DEBUG] 準備更新景點：" + spotVO.toString());
			
			session.merge(spotVO); // Hibernate 6.x 推薦用 merge
			transaction.commit();
			System.out.println("[DAO DEBUG] 景點更新成功");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("[DAO ERROR] 更新景點失敗：" + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("更新景點失敗: " + e.getMessage(), e);
		}
	}

	@Override
	public void delete(Integer spotId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			SpotVO spotVO = session.get(SpotVO.class, spotId);
			if (spotVO != null) {
				System.out.println("[DAO DEBUG] 準備刪除景點：" + spotVO.getSpotName());
				session.remove(spotVO); // Hibernate 6.x 推薦用 remove
			} else {
				System.out.println("[DAO DEBUG] 找不到要刪除的景點，ID：" + spotId);
			}
			transaction.commit();
			System.out.println("[DAO DEBUG] 景點刪除成功");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("[DAO ERROR] 刪除景點失敗：" + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("刪除景點失敗: " + e.getMessage(), e);
		}
	}

	@Override
	public SpotVO findById(Integer spotId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			System.out.println("[DAO DEBUG] 查詢景點，ID：" + spotId);
			SpotVO spotVO = session.get(SpotVO.class, spotId);
			if (spotVO != null) {
				System.out.println("[DAO DEBUG] 找到景點：" + spotVO.getSpotName());
			} else {
				System.out.println("[DAO DEBUG] 找不到景點，ID：" + spotId);
			}
			return spotVO;
		} catch (Exception e) {
			System.err.println("[DAO ERROR] 查詢景點失敗：" + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("查詢景點失敗: " + e.getMessage(), e);
		}
	}

	@Override
	public List<SpotVO> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			System.out.println("[DAO DEBUG] 查詢所有景點");
			Query<SpotVO> query = session.createQuery("FROM SpotVO", SpotVO.class);
			List<SpotVO> spotList = query.getResultList();
			System.out.println("[DAO DEBUG] 查詢到 " + spotList.size() + " 個景點");
			return spotList;
		} catch (Exception e) {
			System.err.println("[DAO ERROR] 查詢所有景點失敗：" + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("查詢所有景點失敗: " + e.getMessage(), e);
		}
	}
}

package com.toiukha.notification.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.toiukha.hibernate.HibernateUtil;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class NotificationDAO implements NotificationDAOInterface {

	private SessionFactory factory;

	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	public NotificationDAO() {
		factory = HibernateUtil.getSessionFactory();
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(NotificationVO notificationVO) {
		getSession().persist(notificationVO);
	}

	@Override
	public void update(NotificationVO notificationVO) {
		getSession().merge(notificationVO);
	}

	@Override
	public void staChange(Integer notiId, Byte notiSta, Timestamp notiUpdatedAt) {
		getSession().createQuery("UPDATE NotificationVO as nVO SET nVO.notiStatus = :notiStatus, nVO.notiUpdatedAt = :notiUpdatedAt WHERE nVO.notiId = :notiId")
				.setParameter("notiId", notiId)
				.setParameter("notiStatus", notiSta)
				.setParameter("notiUpdatedAt", notiUpdatedAt)
				.executeUpdate();
	}

	@Override
	public NotificationVO getOneNoti(Integer notiId) {
		return getSession().find(NotificationVO.class, notiId);
	}

	@Override
	public List<NotificationVO> getByMemId(Integer memId) {
		return getSession().createQuery("from NotificationVO WHERE memId = :memId", NotificationVO.class)
				.setParameter("memId", memId).getResultList();
	}


	@Override
	public List<NotificationVO> getAll() {
		return getSession().createQuery("from NotificationVO", NotificationVO.class).getResultList();
	}
	
	@Override
	public List<NotificationVO> getCompositeQuery(Map<String, String> map) {
		if (map.size() == 0)
			return getAll();
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<NotificationVO> criteria = builder.createQuery(NotificationVO.class);
		Root<NotificationVO> root = criteria.from(NotificationVO.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (map.containsKey("notificationTimeStart") && map.containsKey("notificationTimeEnd"))
			predicates.add(builder.between(root.get("notiSendAt"), Timestamp.valueOf(map.get("notificationTimeStart")), Timestamp.valueOf(map.get("notificationTimeEnd"))));
		
		for (Map.Entry<String, String> row : map.entrySet()) {
			//查詢有該會員編號的通知(複數查詢)
			if ("memIds".equals(row.getKey())) {
				String[] memIdStrings = row.getValue().split(",");
				List<Integer> memIdList = new ArrayList<Integer>();
				
				for(String memId : memIdStrings) {
					memIdList.add(Integer.valueOf(memId));
				}
				
				if(!memIdList.isEmpty()) {
					predicates.add(root.get("memId").in(memIdList));
				}
			}
			
			//如果只填開始時間
			if ("notiSendAtStart".equals(row.getKey())) {
				if(!map.containsKey("notiSendAtEnd")) {
					predicates.add(builder.greaterThanOrEqualTo(root.get("notiSendAt"), Timestamp.valueOf(row.getValue())));
				}
			}
			
			//如果只填結束時間
			if ("notiSendAtEnd".equals(row.getKey())) {
				if(!map.containsKey("notiSendAtStart")) {
					predicates.add(builder.lessThanOrEqualTo(root.get("notiSendAt"), Timestamp.valueOf(row.getValue())));
				}
			}
			
			//標題
			if ("notiTitle".equals(row.getKey())) {
				predicates.add(builder.like(root.get("notiTitle"), "%" + row.getValue() + "%"));
			}
			
			//內容
			if ("notiCont".equals(row.getKey())) {
				predicates.add(builder.like(root.get("notiCont"), "%" + row.getValue() + "%"));
			}
			
			//狀態
			if("notiStatus".equals(row.getKey())) {
					if(row.getValue() == "4") {
						Date date = new Date();
						Timestamp nowTime = new Timestamp(date.getTime());
						predicates.add(builder.greaterThanOrEqualTo(root.get("notiSendAt"), nowTime));
				} else {
					predicates.add(builder.equal(root.get("notiStatus"), row.getValue()));
				}
			}
			
		}
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("memId")), builder.asc(root.get("notiSendAt")));
		TypedQuery<NotificationVO> query = getSession().createQuery(criteria);
		
		return query.getResultList();
		
	}
	
}

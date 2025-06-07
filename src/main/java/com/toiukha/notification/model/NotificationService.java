package com.toiukha.notification.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.toiukha.hibernate.HibernateUtil;

public class NotificationService {
	
	private NotificationDAOInterface notiDAOInterface;
	
	public NotificationService() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		notiDAOInterface = new NotificationDAO();
		session.getTransaction().commit();
	}
	
	public void addNoti(NotificationVO notificationVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		notiDAOInterface.insert(notificationVO);
		session.getTransaction().commit();
	}
	
	public void updateNoti(NotificationVO notificationVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		notiDAOInterface.update(notificationVO);
		session.getTransaction().commit();
	}
	
	public void changeNotiSta(Integer notiId, Byte notiSta, Timestamp notiUpdatedAt) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		notiDAOInterface.staChange(notiId, notiSta, notiUpdatedAt);
		session.getTransaction().commit();
	}
	
	public List<NotificationVO> getAllNoti(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<NotificationVO> list = notiDAOInterface.getAll();
		session.getTransaction().commit();
		return list;
	}
	
	public NotificationVO getOneNoti(Integer notiId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		NotificationVO notificationVO = notiDAOInterface.getOneNoti(notiId);
		session.getTransaction().commit();
		return notificationVO;
	}
	
	public List<NotificationVO> getMemNoti(Integer memId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();	
		List<NotificationVO> list = notiDAOInterface.getByMemId(memId);
		session.getTransaction().commit();
		return list;
	}
	
	public List<NotificationVO> getNotisByCompositeQuery(Map map){
		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value，回傳一個Set裡面就是一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			
			if("memIds".equals(key)) {
				
				String[] valueArray = row.getValue();
				if(valueArray == null || valueArray.length == 0) {
					continue;
				}
				
				String value = String.join(",", valueArray);
				
				
				query.put(key, value);
				
			}else {
				// 若是value為空即代表沒有查詢條件，做個去除動作
				String value = row.getValue()[0]; // getValue拿到一個String陣列, 接著[0]取得第一個元素檢查
				
				if (value == null || value.isEmpty()) {
					continue;
				}
				
				if("notiSendAtStart".equals(key) || "notiSendAtEnd".equals(key)) {
					value = value.replace("T", " ");
				}
				
				query.put(key, value);
			}
			
		}
		
		System.out.println(query);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<NotificationVO> list = notiDAOInterface.getCompositeQuery(query);
		session.getTransaction().commit();
		
		return list;
	}

}

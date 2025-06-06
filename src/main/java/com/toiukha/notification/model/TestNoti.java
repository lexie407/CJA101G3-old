package com.toiukha.notification.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.toiukha.hibernate.HibernateUtil;

public class TestNoti {
	
	public static void main(String[] args) {
		
		NotificationDAO nDAO = new NotificationDAO();
		List<NotificationVO> list = new ArrayList<NotificationVO>();
		NotificationVO notificationVO = new NotificationVO();
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
//		list = nDAO.getByMemId(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dParse = null;
		try {
			dParse = sdf.parse("2025-06-01 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		Timestamp time = new Timestamp(dParse.getTime());
		
//		notificationVO.setNotiId(7);
		notificationVO.setNotiTitle("通知標題0");
		notificationVO.setNotiCont("000");
		notificationVO.setMemId(2);
//		notificationVO.setNotiStatus((byte)0);
		notificationVO.setNotiSendAt(time);
		
		nDAO.insert(notificationVO);
		
		transaction.commit();
		
//		for(NotificationVO nVO : list) {
//			System.out.println(nVO.getNotiId());
//		}
	}

}

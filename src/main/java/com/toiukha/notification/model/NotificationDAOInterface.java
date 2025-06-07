package com.toiukha.notification.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface NotificationDAOInterface {
	
	public void insert(NotificationVO notificationVO);
	public void update(NotificationVO notificationVO);
	public void staChange(Integer notiId, Byte notiSta, Timestamp notiUpdatedAt);
	public NotificationVO getOneNoti(Integer notiId);
	public List<NotificationVO> getByMemId(Integer memId);
	public List<NotificationVO> getCompositeQuery(Map<String, String> map);
	public List<NotificationVO> getAll();

}

package com.toiukha.forum.points.model;

import java.sql.Date;

import java.util.List;

import com.toiukha.forum.points.entity.PointChangesVO;

// 這個介面定義DAO該是什麼格式
public interface IPointChangesDAO {
	public int add(PointChangesVO pcv);
	public void update(PointChangesVO pcv);
//	public void delete(PointChangesVO pVo);
	public PointChangesVO findByPrimaryKey(Integer chaId);
	public List<PointChangesVO> findByDates(Date begin,Date end);
	public List<PointChangesVO> findByMemId(Integer memId);
	public List<PointChangesVO> getAll();
}

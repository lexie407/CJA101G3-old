package com.toiukha.forum.points.model;

import java.util.List;

import com.toiukha.forum.points.entity.PointChangesVO;

public interface IPointChangesService {
	public List<PointChangesVO> getAll();
	public PointChangesVO getPointChange(Integer chaId);
//	public List<PointChangesVO> getPointChanges(Integer memId);
}

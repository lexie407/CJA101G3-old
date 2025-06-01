package com.toiukha.forum.points.model;

import com.toiukha.forum.points.entity.PointChangesVO;

import java.sql.Date;
import java.util.List;

public class PointChangesDAO implements IPointChangesDAO {

    @Override
    public int add(PointChangesVO pcv) {
        return 0;
    }

    @Override
    public void update(PointChangesVO pcv) {

    }

    @Override
    public PointChangesVO findByPrimaryKey(Integer chaId) {
        return null;
    }

    @Override
    public List<PointChangesVO> findByDates(Date begin, Date end) {
        return List.of();
    }

    @Override
    public List<PointChangesVO> findByMemId(Integer memId) {
        return List.of();
    }

    @Override
    public List<PointChangesVO> getAll() {
        return List.of();
    }
}

package com.toiukha.forum.points.model;

import java.util.List;

import com.toiukha.forum.points.entity.PointChangesVO;

// 據說負責寫驗證、商業邏輯等等，只是在getAll方法裡他沒有邏輯要寫……
public class PointChangesService implements IPointChangesService {
	private IPointChangesDAO dao;
	
	//建構子
	public PointChangesService() {
		dao = new PointChangesJDBCDAO();
	}
	
	@Override
	public List<PointChangesVO> getAll() {
		return dao.getAll();
	}

	@Override
	public PointChangesVO getPointChange(Integer chaId) {
		return dao.findByPrimaryKey(chaId);
	}

	//Service測試
//	public static void main(String[] args) {
//		PointChangesService service = new PointChangesService();
//		PointChangesVO vo = service.getPointChange(5);
//
//		System.out.println(vo);
//	}

}

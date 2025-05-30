package com.toiukha.order.model;

import java.util.List;

public class OrderService {
	private OrderDAO_interface dao;

	public OrderService() {
		dao = new OrderJDBCDAO();
	}

	public OrderVO addOrder(Integer memid, Integer ordsta, java.sql.Timestamp credate) {
		OrderVO orderVO = new OrderVO();

		orderVO.setMemId(memid);
		orderVO.setOrdSta(ordsta);
		orderVO.setCreDate(credate);

		dao.insert(orderVO);
		return orderVO;
	}

	public OrderVO updateOrd(Integer ordid, Integer memid, Integer ordsta, java.sql.Timestamp credate) {
		OrderVO orderVO = new OrderVO();
		orderVO.setOrdId(ordid);
		orderVO.setMemId(memid);
		orderVO.setOrdSta(ordsta);
		orderVO.setCreDate(credate);

		dao.update(orderVO);
		return orderVO;
	}

	public void deleteOrd(Integer ordid) {
		OrderVO orderVO = new OrderVO();
		dao.delete(ordid);
	}

	public OrderVO getOneOrder(Integer ordid) {
		return dao.findByPrimaryKey(ordid);
	}

	public List<OrderVO> getAll() {
		return dao.getAll();
	}
}

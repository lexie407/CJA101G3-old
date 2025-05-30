package com.toiukha.order.model;

import java.util.List;

public interface OrderDAO_interface {
	public void insert(OrderVO orderVO);

	public void update(OrderVO orderVO);

	public void delete(Integer ordid);

	public OrderVO findByPrimaryKey(Integer ordid);

	public List<OrderVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 

}

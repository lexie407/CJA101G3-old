package com.toiukha.forum.points.model;

import java.util.*;

import com.toiukha.forum.points.entity.ExchangeItemsVO;

public interface IExchangeItemsDAO  {
	public void insert(ExchangeItemsVO eItemsVO);
	public void update(ExchangeItemsVO eItemsVO);
	public void delete(ExchangeItemsVO eItemsVO);
	public ExchangeItemsVO findByPrimaryKey(ExchangeItemsVO eItemsVO);
	public List<ExchangeItemsVO> getAll();
}

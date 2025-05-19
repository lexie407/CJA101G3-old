package com.toiukha.forum.points.model;

import com.toiukha.forum.points.entity.ExchangeItemsVO;

import java.util.*;

public interface IExchaItemDAO {
	public void insert(ExchangeItemsVO eItemsVO);
	public void update(ExchangeItemsVO eItemsVO);
	public void delete(ExchangeItemsVO eItemsVO);
	public ExchangeItemsVO findByPrimaryKey(ExchangeItemsVO eItemsVO);
	public List<ExchangeItemsVO> getAll();

}

package com.toiukha.spot.model;

import java.util.*;

public interface SpotDAO_interface {
	void insert(SpotVO spotVO);

	void update(SpotVO spotVO);

	void delete(Integer spotId); // ← 新增這一行

	SpotVO findByPrimaryKey(Integer spotId);

	List<SpotVO> getAll();
}

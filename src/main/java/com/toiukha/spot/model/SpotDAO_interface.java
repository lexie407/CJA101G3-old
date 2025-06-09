package com.toiukha.spot.model;

import java.util.List;

public interface SpotDAO_interface {
	void insert(SpotVO spotVO);
    void update(SpotVO spotVO);
    void delete(Integer spotId);
    SpotVO findById(Integer spotId);
    List<SpotVO> getAll(); 
}

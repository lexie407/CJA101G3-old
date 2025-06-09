package com.toiukha.spot.model;

import java.util.List;

public interface SpotService_interface {
	void addSpot(SpotVO spotVO);

	void updateSpot(SpotVO spotVO);

	void deleteSpot(Integer spotId);

	SpotVO getOneSpot(Integer spotId);

	List<SpotVO> getAllSpots();
}

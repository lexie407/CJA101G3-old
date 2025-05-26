package com.toiukha.spot.model;

import java.util.List;

public class SpotService {

	private SpotDAO_interface dao;

	public SpotService() {
		dao = new SpotJDBCDAO(); // 直接使用 JDBC 版本
	}

	// 新增景點
	public SpotVO addSpot(String spotName, Integer crtId, String spotLoc, Double spotLat, Double spotLng,
			Byte spotStatus, String spotDesc) {
		SpotVO spotVO = new SpotVO();
		spotVO.setSpotName(spotName);
		spotVO.setCrtId(crtId);
		spotVO.setSpotLoc(spotLoc);
		spotVO.setSpotLat(spotLat);
		spotVO.setSpotLng(spotLng);
		spotVO.setSpotStatus(spotStatus);
		spotVO.setSpotDesc(spotDesc);
		dao.insert(spotVO);
		return spotVO;
	}

	// 修改景點
	public SpotVO updateSpot(Integer spotId, String spotName, Integer crtId, String spotLoc, Double spotLat,
			Double spotLng, Byte spotStatus, String spotDesc) {
		SpotVO spotVO = new SpotVO();
		spotVO.setSpotId(spotId);
		spotVO.setSpotName(spotName);
		spotVO.setCrtId(crtId);
		spotVO.setSpotLoc(spotLoc);
		spotVO.setSpotLat(spotLat);
		spotVO.setSpotLng(spotLng);
		spotVO.setSpotStatus(spotStatus);
		spotVO.setSpotDesc(spotDesc);
		dao.update(spotVO);
		return spotVO;
	}

	// 刪除景點
	public void deleteSpot(Integer spotId) {
		dao.delete(spotId);
	}

	// 查單一景點
	public SpotVO getOneSpot(Integer spotId) {
		return dao.findByPrimaryKey(spotId);
	}

	// 查全部景點
	public List<SpotVO> getAll() {
		return dao.getAll();
	}
}

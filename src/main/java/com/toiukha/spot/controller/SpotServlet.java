package com.toiukha.spot.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.toiukha.spot.model.SpotService;
import com.toiukha.spot.model.SpotVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SpotServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自 select_page.jsp 的查詢請求
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
		
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("spotId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入景點編號");
//				====================================================				
//				throw new RuntimeException("測試 500 錯誤頁");//測試500
//				====================================================
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/spot/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer spotId = null;
			try {
			    spotId = Integer.valueOf(str);
			} catch (Exception e) {
			    errorMsgs.add("景點編號格式不正確");
			    RequestDispatcher failureView = req.getRequestDispatcher("/spot/select_page.jsp");
			    failureView.forward(req, res);
			    return;
			}
		
		

			/*************************** 2.開始查詢資料 *****************************************/
			SpotService spotSvc = new SpotService();
			SpotVO spotVO = spotSvc.getOneSpot(spotId);
			if (spotVO == null) {
				errorMsgs.add("查無資料");
				RequestDispatcher failureView = req.getRequestDispatcher("/spot/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("spotVO", spotVO);
			String url = "/spot/listOneSpot.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自 listAllSpot.jsp 的修改請求
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer spotId = Integer.valueOf(req.getParameter("spotId"));

			/*************************** 2.開始查詢資料 ****************************************/
			SpotService spotSvc = new SpotService();
			SpotVO spotVO = spotSvc.getOneSpot(spotId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("spotVO", spotVO);
			String url = "/spot/update_spot_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自 update_spot_input.jsp 的修改提交
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer spotId = Integer.valueOf(req.getParameter("spotId").trim());
			String spotName = req.getParameter("spotName");
			Integer crtId = Integer.valueOf(req.getParameter("crtId").trim());
			String spotLoc = req.getParameter("spotLoc");
			Double spotLat = Double.valueOf(req.getParameter("spotLat").trim());
			Double spotLng = Double.valueOf(req.getParameter("spotLng").trim());
			Byte spotStatus = Byte.valueOf(req.getParameter("spotStatus").trim());
			String spotDesc = req.getParameter("spotDesc");

			// 驗證邏輯可自行擴充
			if (spotName == null || spotName.trim().length() == 0) {
				errorMsgs.add("景點名稱請勿空白");
			}

			SpotVO spotVO = new SpotVO();
			spotVO.setSpotId(spotId);
			spotVO.setSpotName(spotName);
			spotVO.setCrtId(crtId);
			spotVO.setSpotLoc(spotLoc);
			spotVO.setSpotLat(spotLat);
			spotVO.setSpotLng(spotLng);
			spotVO.setSpotStatus(spotStatus);
			spotVO.setSpotDesc(spotDesc);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("spotVO", spotVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/spot/update_spot_input.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			SpotService spotSvc = new SpotService();
			spotVO = spotSvc.updateSpot(spotId, spotName, crtId, spotLoc, spotLat, spotLng, spotStatus, spotDesc);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("spotVO", spotVO);
			String url = "/spot/listOneSpot.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自 addSpot.jsp 的新增請求
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String spotName = req.getParameter("spotName");
			Integer crtId = Integer.valueOf(req.getParameter("crtId").trim());
			String spotLoc = req.getParameter("spotLoc");
			Double spotLat = Double.valueOf(req.getParameter("spotLat").trim());
			Double spotLng = Double.valueOf(req.getParameter("spotLng").trim());
			Byte spotStatus = Byte.valueOf(req.getParameter("spotStatus").trim());
			String spotDesc = req.getParameter("spotDesc");

			// 驗證邏輯
			if (spotName == null || spotName.trim().length() == 0) {
				errorMsgs.add("景點名稱請勿空白");
			}

			SpotVO spotVO = new SpotVO();
			spotVO.setSpotName(spotName);
			spotVO.setCrtId(crtId);
			spotVO.setSpotLoc(spotLoc);
			spotVO.setSpotLat(spotLat);
			spotVO.setSpotLng(spotLng);
			spotVO.setSpotStatus(spotStatus);
			spotVO.setSpotDesc(spotDesc);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("spotVO", spotVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/spot/addSpot.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 *****************************************/
			SpotService spotSvc = new SpotService();
			spotSvc.addSpot(spotName, crtId, spotLoc, spotLat, spotLng, spotStatus, spotDesc);

			/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
			String url = "/spot/listAllSpot.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自 listAllSpot.jsp 的刪除請求
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer spotId = Integer.valueOf(req.getParameter("spotId"));

			/*************************** 2.開始刪除資料 ***************************************/
			SpotService spotSvc = new SpotService();
			spotSvc.deleteSpot(spotId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/spot/listAllSpot.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}

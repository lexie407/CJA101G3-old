package com.toiukha.notification.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.toiukha.notification.model.NotificationService;
import com.toiukha.notification.model.NotificationVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "NotificationServlet", urlPatterns = {"/notification/noti.do"})
public class NotificationServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			doPost(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		List<String> errorMsgs = new ArrayList<String>();
		
		/*====================使用者端====================*/
		
		//-------------------- 查看通知詳細資訊 --------------------//
		if("getNotiDetail".equals(action)) {
			
			//接收資料
			Integer notiId = Integer.valueOf(req.getParameter("notiId"));
			Byte notiStatus = Byte.valueOf(req.getParameter("notiStatus"));
			
			//查詢資料並修改狀態
			NotificationService notificationService = new NotificationService();
			NotificationVO notificationVO = notificationService.getOneNoti(notiId);
			
			if(notiStatus == 0) {
				notificationService.changeNotiSta(notiId, (byte)1, nowTime());
			}
			
			//包裝資料
			req.setAttribute("notificationVO", notificationVO);
			
			//轉送頁面
			RequestDispatcher dispatcher = req.getRequestDispatcher("/notification/front/membersDetailNotification.jsp");
			dispatcher.forward(req, res);
			return;
		}
		
		//-------------------- 刪除所選通知 --------------------//
		
		if("deleteNoti".equals(action)) {
			//接收資料
			String[] notiIds = req.getParameterValues("notiIds");
			
			//刪除通知
			NotificationService notificationService = new NotificationService();
			for(int i = 0; i < notiIds.length; i++) {
				notificationService.changeNotiSta(Integer.valueOf(notiIds[i]), (byte)2, nowTime());
			}
			
			//轉送頁面
			RequestDispatcher dispatcher = req.getRequestDispatcher("/notification/front/membersNotification.jsp");
			dispatcher.forward(req, res);
			return;
		}
		
		/*====================管理員端====================*/
		
		//-------------------- 顯示詳細通知資訊頁面 --------------------//
		
		if("editNoti".equals(action)) {
			//接收資料
			Integer notiId = Integer.valueOf(req.getParameter("notiId"));
			
			//查詢資料
			NotificationService notificationService = new NotificationService();
			NotificationVO notificationVO = notificationService.getOneNoti(notiId);
			
			//轉交資料
			req.setAttribute("originalUri", req.getParameter("originalUri"));
			req.setAttribute("notificationVO", notificationVO);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/notification/back/notiEdit.jsp");
			dispatcher.forward(req, res);
			return;
		}
		
		//-------------------- 更新通知資訊 --------------------//
		
		if("updateNoti".equals(action)) {
			//接收資料
			Integer notiId = Integer.valueOf(req.getParameter("notiId"));
			String notiTitle = req.getParameter("notiTitle");
			String notiCont = req.getParameter("notiCont");
			Integer memId = Integer.valueOf(req.getParameter("memId"));
			Byte notiStatus = Byte.valueOf(req.getParameter("notiStatus"));
			Integer adminId = Integer.valueOf(req.getParameter("adminId"));
			Timestamp notiUpdatedAt = nowTime();
			Timestamp notiSendAt = datePrase(req.getParameter("notiSendAt"));
			NotificationVO notificationVO = new NotificationVO(notiId, notiTitle, notiCont, memId, notiStatus, adminId, notiUpdatedAt, notiSendAt);
			
			//更新資料
			NotificationService notificationService = new NotificationService();
			notificationService.updateNoti(notificationVO);
			
			//傳交資料
			RequestDispatcher dispatcher = req.getRequestDispatcher(req.getParameter("originalUri"));
			dispatcher.forward(req, res);
			return;
		}
		
		//-------------------- 刪除通知 --------------------//
		
		if("deleteNotiForAdmin".equals(action)) {
			//接收資料
			Integer notiId = Integer.valueOf(req.getParameter("notiId"));
			
			//刪除通知
			NotificationService notificationService = new NotificationService();
			notificationService.changeNotiSta(notiId, (byte)3, nowTime());
			
			//轉交資料
			RequestDispatcher dispatcher = req.getRequestDispatcher(req.getParameter("originalUri"));
			dispatcher.forward(req, res);
			return;
			
		}
		
		//-------------------- 新增通知 --------------------//
		
		if("createNoti".equalsIgnoreCase(action)) {
			//接收資料
			String notiTitle = req.getParameter("notiTitle");
			String notiCont = req.getParameter("notiCont");
			String[] memIds = req.getParameterValues("memIds");
			Integer adminId = Integer.valueOf(req.getParameter("adminId"));
			Timestamp notiSendAt = datePrase(req.getParameter("notiSendAt"));
			
			for(int i = 0; i < memIds.length; i++) {
				Integer memId = Integer.valueOf(memIds[i]);
				NotificationVO notificationVO = new NotificationVO(notiTitle, notiCont, memId, adminId, notiSendAt);
				//新增通知
				NotificationService notificationService = new NotificationService();
				notificationService.addNoti(notificationVO);
			}
			
			//轉交資料
			RequestDispatcher dispatcher = req.getRequestDispatcher("/notification/back/notiCreate.jsp");
			dispatcher.forward(req, res);
			return;
		}
		
		//-------------------- 查詢通知 --------------------//
		
		if("searchNoti".equalsIgnoreCase(action)) {
			//接收資料
			Map<String, String[]> map = req.getParameterMap();
			
			//查詢資料
			NotificationService notificationService = new NotificationService();
			List<NotificationVO> list = notificationService.getNotisByCompositeQuery(map);
			
			//轉交資料
			req.setAttribute("map", map);
			req.setAttribute("list", list);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/notification/back/notiSearch.jsp");
			dispatcher.forward(req, res);
			return;
			}
		}
		
	//轉換req的Date資料
	public Timestamp datePrase(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date prase = null;
		try {
			prase = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp time = new Timestamp(prase.getTime());
		return time;
	}
	
	//現在時間
	public Timestamp nowTime() {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		return time;
	}
	
}

package com.toiukha.spot.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.toiukha.spot.model.SpotService_Impl;
import com.toiukha.spot.model.SpotService_interface;
import com.toiukha.spot.model.SpotVO;
import com.toiukha.spot.util.NumberUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SpotServlet")
public class SpotServlet extends HttpServlet {
	private SpotService_interface SpotService_interface;

	@Override
	public void init() throws ServletException {
		SpotService_interface = new SpotService_Impl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	        throws ServletException, IOException {
	    System.out.println("[Servlet DEBUG] 收到 GET 請求");
	    String action = req.getParameter("action");
	    System.out.println("[Servlet DEBUG] GET action = " + action);
	    
	    // 如果沒有 action 或 action 是 listAll，直接顯示列表
	    if (action == null || action.equals("listAll")) {
	        listAllSpots(req, res);
	        return;
	    }
	    
	    // 其他 GET 請求處理
	    switch (action) {
	        case "getOne_For_Display":
	            getOneForDisplay(req, res);
	            break;
	        case "getOne_For_Update":
	            getOneForUpdate(req, res);
	            break;
	        default:
	            listAllSpots(req, res);
	    }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
	        throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    String action = req.getParameter("action");
	    String contextPath = req.getContextPath();
	    
	    System.out.println("[Servlet DEBUG] 收到 POST 請求，action = " + action);

	    // 處理 action 為空的情況
	    if (action == null) {
	        listAllSpots(req, res);
	        return;
	    }

	    try {
	        switch (action) {
	            case "getOne_For_Display":
	                getOneForDisplay(req, res);
	                break;
	            case "getOne_For_Update":
	                getOneForUpdate(req, res);
	                break;
	            case "updateSpot":
	                updateSpot(req, res, contextPath);
	                break;
	            case "addSpot":
	                insertSpot(req, res, contextPath);
	                break;
	            case "delete":
	                deleteSpot(req, res, contextPath);
	                break;
	            case "listAll":
	                listAllSpots(req, res);
	                break;
	            default:
	                System.out.println("[Servlet DEBUG] 未知操作，轉到列表頁面: " + action);
	                listAllSpots(req, res);
	        }
	    } catch (Exception e) {
	        System.err.println("[Servlet ERROR] 系統錯誤：" + e.getMessage());
	        e.printStackTrace();
	        handleError(req, res, "系統錯誤：" + e.getMessage());
	    }
	}

	// 查詢單筆資料顯示
	private void getOneForDisplay(HttpServletRequest req, HttpServletResponse res)
	        throws ServletException, IOException {
	    System.out.println("[Servlet DEBUG] 執行 getOneForDisplay");
	    
	    List<String> errorMsgs = new LinkedList<>();
	    req.setAttribute("errorMsgs", errorMsgs);

	    String str = req.getParameter("spotId");
	    System.out.println("[Servlet DEBUG] 接收到 spotId = " + str);
	    
	    if (str == null || str.trim().isEmpty()) {
	        errorMsgs.add("請輸入景點編號");
	    }

	    Integer spotId = NumberUtil.safeParseInt(str);
	    if (spotId == null) {
	        errorMsgs.add("景點編號格式不正確");
	    }

	    if (!errorMsgs.isEmpty()) {
	        System.out.println("[Servlet DEBUG] 驗證失敗，返回查詢頁面");
	        RequestDispatcher failureView = req.getRequestDispatcher("/spot/select_page.jsp");
	        failureView.forward(req, res);
	        return;
	    }

	    SpotVO spotVO = SpotService_interface.getOneSpot(spotId);
	    if (spotVO == null) {
	        System.out.println("[Servlet DEBUG] 查無資料，spotId = " + spotId);
	        errorMsgs.add("查無資料");
	        RequestDispatcher failureView = req.getRequestDispatcher("/spot/select_page.jsp");
	        failureView.forward(req, res);
	        return;
	    }

	    System.out.println("[Servlet DEBUG] 查詢成功，景點名稱：" + spotVO.getSpotName());
	    req.setAttribute("spotVO", spotVO);
	    RequestDispatcher successView = req.getRequestDispatcher("/spot/listOneSpot.jsp");
	    successView.forward(req, res);
	}

	// 查詢單筆資料供修改
	private void getOneForUpdate(HttpServletRequest req, HttpServletResponse res) 
	        throws ServletException, IOException {
	    System.out.println("[Servlet DEBUG] 執行 getOneForUpdate");
	    
	    Integer spotId = NumberUtil.safeParseInt(req.getParameter("spotId"));
	    if (spotId == null) {
	        handleError(req, res, "景點編號格式錯誤");
	        return;
	    }

	    SpotVO spotVO = SpotService_interface.getOneSpot(spotId);
	    if (spotVO == null) {
	        handleError(req, res, "查無此景點資料");
	        return;
	    }

	    req.setAttribute("spotVO", spotVO);
	    RequestDispatcher successView = req.getRequestDispatcher("/spot/update_spot_input.jsp");
	    successView.forward(req, res);
	}



	// 修改後的 insertSpot 方法 (修正重定向路徑)
	private void insertSpot(HttpServletRequest req, HttpServletResponse res, String contextPath)
			throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<>(); // 必須在方法開頭初始化
		req.setAttribute("errorMsgs", errorMsgs);

		// 接收參數時增加trim()處理空白
		String spotName = req.getParameter("spotName") != null ? req.getParameter("spotName").trim() : "";
		String spotLoc = req.getParameter("spotLoc") != null ? req.getParameter("spotLoc").trim() : "";

		// 強化驗證邏輯
		if (spotName.isEmpty()) {
			errorMsgs.add("景點名稱必填");
		}
		Integer crtId = NumberUtil.safeParseInt(req.getParameter("crtId"));
		if (crtId == null || crtId <= 0) { // 增加ID合理性檢查
			errorMsgs.add("建立者ID必須是有效正整數");
		}
		if (spotLoc.isEmpty()) {
			errorMsgs.add("地址必填");
		}

		// 封裝物件前處理預設值
		Byte spotStatus = NumberUtil.safeParseByte(req.getParameter("spotStatus"));
		if (spotStatus == null) {
			spotStatus = 0; // 明確設定預設值
		}

		SpotVO spotVO = new SpotVO();
		spotVO.setSpotName(spotName);
		spotVO.setCrtId(crtId);
		spotVO.setSpotLoc(spotLoc);
		spotVO.setSpotStatus(spotStatus);

		// 非必填欄位處理
		String spotDesc = req.getParameter("spotDesc");
		if (spotDesc != null && !spotDesc.isEmpty()) {
			spotVO.setSpotDesc(spotDesc.trim());
		}

		// 提前返回錯誤
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("spotVO", spotVO);
			req.getRequestDispatcher("/spot/addSpot.jsp").forward(req, res);
			return;
		}

		try {
			SpotService_interface.addSpot(spotVO);
			// 重定向後強制刷新快取
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			res.sendRedirect(contextPath + "/SpotServlet?action=listAll");
		} catch (Exception e) {
			errorMsgs.add("新增失敗：" + e.getMessage());
			req.setAttribute("spotVO", spotVO);
			req.getRequestDispatcher("/spot/addSpot.jsp").forward(req, res);
		}
	}

	// 更新景點
	// 修改後的 updateSpot 方法 (改用重定向)
	private void updateSpot(HttpServletRequest req, HttpServletResponse res, String contextPath)
			throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<>(); // 必須在方法開頭初始化
		req.setAttribute("errorMsgs", errorMsgs);

		// 加強spotId驗證
		Integer spotId = NumberUtil.safeParseInt(req.getParameter("spotId"));
		if (spotId == null || spotId <= 0) {
			errorMsgs.add("景點編號格式錯誤");
			req.getRequestDispatcher("/spot/update_spot_input.jsp").forward(req, res);
			return;
		}

		// 接收參數時處理空白
		String spotName = req.getParameter("spotName") != null ? req.getParameter("spotName").trim() : "";
		String spotLoc = req.getParameter("spotLoc") != null ? req.getParameter("spotLoc").trim() : "";

		// 驗證必填欄位
		if (spotName.isEmpty()) {
			errorMsgs.add("景點名稱必填");
		}
		if (spotLoc.isEmpty()) {
			errorMsgs.add("地址必填");
		}

		// 從資料庫獲取原始資料補齊其他欄位
		SpotVO originalSpot = SpotService_interface.getOneSpot(spotId);
		if (originalSpot == null) {
			errorMsgs.add("查無此景點資料");
			req.getRequestDispatcher("/spot/update_spot_input.jsp").forward(req, res);
			return;
		}

		// 只更新允許修改的欄位
		originalSpot.setSpotName(spotName);
		originalSpot.setSpotLoc(spotLoc);

		// 處理狀態欄位
		Byte spotStatus = NumberUtil.safeParseByte(req.getParameter("spotStatus"));
		if (spotStatus != null) {
			originalSpot.setSpotStatus(spotStatus);
		}

		// 非必填欄位
		String spotDesc = req.getParameter("spotDesc");
		if (spotDesc != null && !spotDesc.isEmpty()) {
			originalSpot.setSpotDesc(spotDesc.trim());
		}

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("spotVO", originalSpot);
			req.getRequestDispatcher("/spot/update_spot_input.jsp").forward(req, res);
			return;
		}

		try {
			SpotService_interface.updateSpot(originalSpot); // 傳遞從DB取得的物件
			// 重定向時禁用瀏覽器快取
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			res.sendRedirect(contextPath + "/SpotServlet?action=getOne_For_Display&spotId=" + spotId);
		} catch (Exception e) {
			errorMsgs.add("更新失敗：" + e.getMessage());
			req.setAttribute("spotVO", originalSpot);
			req.getRequestDispatcher("/spot/update_spot_input.jsp").forward(req, res);
		}
	}

	// 刪除景點
	private void deleteSpot(HttpServletRequest req, HttpServletResponse res, String contextPath)
			throws ServletException, IOException {
		Integer spotId = NumberUtil.safeParseInt(req.getParameter("spotId"));
		if (spotId == null) {
			handleError(req, res, "景點編號格式錯誤");
			return;
		}

		try {
			SpotService_interface.deleteSpot(spotId);
			res.sendRedirect(contextPath + "/SpotServlet?action=listAll");
		} catch (Exception e) {
			handleError(req, res, "刪除失敗：" + e.getMessage());
		}
	}

	// 列出所有景點
	private void listAllSpots(HttpServletRequest req, HttpServletResponse res) 
	        throws ServletException, IOException {
	    System.out.println("[Servlet DEBUG] 開始執行 listAllSpots()");
	    
	    try {
	        // 呼叫 Service 查詢資料
	        List<SpotVO> spotList = SpotService_interface.getAllSpots();
	        System.out.println("[Servlet DEBUG] Service 查詢結果：" + spotList.size() + " 筆資料");
	        
	        // 印出每筆資料確認內容
	        for (SpotVO spot : spotList) {
	            System.out.println("[Servlet DEBUG] 景點：ID=" + spot.getSpotId() + 
	                             ", 名稱=" + spot.getSpotName() + 
	                             ", 地址=" + spot.getSpotLoc());
	        }
	        
	        // 設定到 request 屬性
	        req.setAttribute("spotList", spotList);
	        System.out.println("[Servlet DEBUG] 已設定 spotList 到 request，大小：" + spotList.size());
	        
	        // 轉發到 JSP
	        String jspPath = "/spot/listAllSpot.jsp";
	        System.out.println("[Servlet DEBUG] 準備轉發至：" + jspPath);
	        
	        RequestDispatcher successView = req.getRequestDispatcher(jspPath);
	        successView.forward(req, res);
	        System.out.println("[Servlet DEBUG] 已成功轉發到 JSP");
	        
	    } catch (Exception e) {
	        System.err.println("[Servlet ERROR] 查詢失敗：" + e.getMessage());
	        e.printStackTrace();
	        handleError(req, res, "查詢失敗：" + e.getMessage());
	    }
	}

	// 完整的錯誤處理方法
	private void handleError(HttpServletRequest req, HttpServletResponse res, String errorMsg) 
	        throws ServletException, IOException {
	    System.err.println("[Servlet ERROR] " + errorMsg);
	    
	    List<String> errorMsgs = new LinkedList<>();
	    errorMsgs.add(errorMsg);
	    req.setAttribute("errorMsgs", errorMsgs);
	    
	    RequestDispatcher errorView = req.getRequestDispatcher("/spot/error.jsp");
	    errorView.forward(req, res);
	}
}

package com.toiukha.forum.article.controller;

import com.toiukha.forum.article.dto.ArticleDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.toiukha.forum.article.model.ArticleService;
import com.toiukha.forum.article.model.IArticleService;
import com.toiukha.forum.util.Debug;

// 也可以用web.xml去設定
@WebServlet("/articles/*")
public class ArticleServlet extends HttpServlet {
	private IArticleService as = null;
	
	private static final long serialVersionUID = 2683579141271862550L;

	
	@Override
	public void init() throws ServletException {
//		super.init();
		as = new ArticleService();
	}

	// 直接用網址輸入、點超連結會進入Get請求
	@Override
	protected void doGet(HttpServletRequest res, HttpServletResponse rep) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8"); 		// 編碼改成UTF-8防止中文亂碼
		String reqURI = res.getRequestURI();	// 等於是整串網址扣掉domain(網站名/網域名)再扣掉?後面
		
		// 針對直接用網址傳的Get請求，去得到action
//		String action = reqURI.substring(reqURI.lastIndexOf("/") + 1);	//相當於把最後一個「/」前面的字串全部削掉
////		String action = res.getParameter("action");	//處理doGet請求(網址後綴?key=value)

		Debug.log();
		rep.setContentType("text/html; charset=UTF-8"); // 設定回傳的資料格式是 HTML 文件(MIME Type，也叫Content-Type )，並且使用 UTF-8 編碼（防止中文亂碼）。
		res.getRequestDispatcher(getAllArticles(res)).forward(res,rep);

//		rep.getWriter().append("Served at: ").append(res.getContextPath()).append(action);
//		doPost(res, rep);
	}

	@Override
	protected void doPost(HttpServletRequest res, HttpServletResponse rep) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8"); 		// 編碼改成UTF-8防止中文亂碼
		String reqURI = res.getRequestURI();	// 等於是整串網址扣掉domain(網站名/網域名)再扣掉?後面

		// 回應會回傳Served at: 專案名稱
		rep.getWriter().append("Served at: ").append(res.getContextPath()).append(reqURI);
	}

	private String getAllArticles(HttpServletRequest res) {

		List<ArticleDTO> articles = as.getAllDTO();
		res.setAttribute("articlesList", articles);
		return "/back-end/forum/article/allArticles.jsp";

	}


}

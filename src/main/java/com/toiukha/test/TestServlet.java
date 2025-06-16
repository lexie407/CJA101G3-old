package com.toiukha.test;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet測試</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
        out.println(".success { color: green; background-color: #d4edda; padding: 10px; border-radius: 5px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>🎉 Servlet測試成功！</h1>");
        out.println("<div class='success'>");
        out.println("✅ 如果您看到這個頁面，表示Servlet可以正常運行");
        out.println("</div>");
        out.println("<p><strong>請求信息：</strong></p>");
        out.println("<ul>");
        out.println("<li>Context Path: " + request.getContextPath() + "</li>");
        out.println("<li>Servlet Path: " + request.getServletPath() + "</li>");
        out.println("<li>Request URI: " + request.getRequestURI() + "</li>");
        out.println("</ul>");
        out.println("<p><a href='" + request.getContextPath() + "/spot/SpotServlet?action=listAll'>測試景點列表</a></p>");
        out.println("</body>");
        out.println("</html>");
    }
} 
package com.toiukha.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SimpleSpotServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>簡單景點測試</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
        out.println("table { border-collapse: collapse; width: 100%; }");
        out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println(".success { color: green; background-color: #d4edda; padding: 10px; border-radius: 5px; }");
        out.println(".error { color: red; background-color: #f8d7da; padding: 10px; border-radius: 5px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>🏞️ 簡單景點列表測試</h1>");
        
        // 資料庫連接參數
        String url = "jdbc:mysql://localhost:3306/toiukha?serverTimezone=Asia/Taipei";
        String username = "root";
        String password = "necz1X23c4V";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM spot ORDER BY SPOTID");
            
            out.println("<div class='success'>✅ 直接從資料庫查詢成功！</div>");
            out.println("<h2>📋 景點列表：</h2>");
            out.println("<table>");
            out.println("<tr><th>ID</th><th>名稱</th><th>建立者ID</th><th>地址</th><th>狀態</th></tr>");
            
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("SPOTID") + "</td>");
                out.println("<td>" + rs.getString("SPOTNAME") + "</td>");
                out.println("<td>" + rs.getInt("CRTID") + "</td>");
                out.println("<td>" + rs.getString("SPOTLOC") + "</td>");
                out.println("<td>" + (rs.getByte("SPOTSTATUS") == 1 ? "啟用" : "停用") + "</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            out.println("<div class='error'>❌ 查詢失敗: " + e.getMessage() + "</div>");
            e.printStackTrace();
        }
        
        out.println("<br><br>");
        out.println("<p><a href='" + request.getContextPath() + "/spot/SpotServlet?action=listAll'>測試原始SpotServlet</a></p>");
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
} 
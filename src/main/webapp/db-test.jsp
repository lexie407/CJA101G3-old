<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>資料庫連接測試</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .success { color: green; background-color: #d4edda; padding: 10px; border-radius: 5px; margin: 10px 0; }
        .error { color: red; background-color: #f8d7da; padding: 10px; border-radius: 5px; margin: 10px 0; }
        .info { color: blue; background-color: #e7f3ff; padding: 10px; border-radius: 5px; margin: 10px 0; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h1>🔍 資料庫連接測試</h1>
    
    <%
        String url = "jdbc:mysql://localhost:3306/toiukha?serverTimezone=Asia/Taipei";
        String username = "root";
        String password = "necz1X23c4V";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            out.println("<div class='info'>🔗 嘗試連接資料庫...</div>");
            out.println("<p><strong>URL:</strong> " + url + "</p>");
            out.println("<p><strong>用戶名:</strong> " + username + "</p>");
            
            // 載入MySQL驅動程式
            Class.forName("com.mysql.cj.jdbc.Driver");
            out.println("<div class='success'>✅ MySQL驅動程式載入成功</div>");
            
            // 連接資料庫
            conn = DriverManager.getConnection(url, username, password);
            out.println("<div class='success'>✅ 資料庫連接成功！</div>");
            
            stmt = conn.createStatement();
            
            // 檢查資料庫是否存在
            rs = stmt.executeQuery("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'toiukha'");
            if (rs.next()) {
                out.println("<div class='success'>✅ 找到資料庫 'toiukha'</div>");
            } else {
                out.println("<div class='error'>❌ 找不到資料庫 'toiukha'</div>");
            }
            rs.close();
            
            // 檢查資料表是否存在
            rs = stmt.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'toiukha' AND TABLE_NAME = 'spot'");
            if (rs.next()) {
                out.println("<div class='success'>✅ 找到資料表 'spot'</div>");
                
                // 檢查資料表中的資料數量
                rs.close();
                rs = stmt.executeQuery("SELECT COUNT(*) FROM toiukha.spot");
                if (rs.next()) {
                    int count = rs.getInt(1);
                    out.println("<div class='info'>📊 spot資料表中有 " + count + " 筆資料</div>");
                    
                    if (count > 0) {
                        out.println("<div class='success'>✅ 資料表有資料，應用程式應該可以正常運作</div>");
                        
                        // 顯示前5筆資料
                        rs.close();
                        rs = stmt.executeQuery("SELECT * FROM toiukha.spot LIMIT 5");
                        out.println("<h3>📋 前5筆景點資料：</h3>");
                        out.println("<table>");
                        out.println("<tr><th>ID</th><th>名稱</th><th>建立者ID</th><th>地址</th><th>狀態</th></tr>");
                        while (rs.next()) {
                            out.println("<tr>");
                            out.println("<td>" + rs.getInt("SPOTID") + "</td>");
                            out.println("<td>" + rs.getString("SPOTNAME") + "</td>");
                            out.println("<td>" + rs.getInt("CRTID") + "</td>");
                            out.println("<td>" + rs.getString("SPOTLOC") + "</td>");
                            out.println("<td>" + rs.getByte("SPOTSTATUS") + "</td>");
                            out.println("</tr>");
                        }
                        out.println("</table>");
                    } else {
                        out.println("<div class='error'>⚠️ 資料表是空的，需要添加測試資料</div>");
                    }
                }
            } else {
                out.println("<div class='error'>❌ 找不到資料表 'spot'</div>");
                out.println("<div class='info'>💡 可能需要先創建資料表</div>");
            }
            
        } catch (ClassNotFoundException e) {
            out.println("<div class='error'>❌ MySQL驅動程式載入失敗: " + e.getMessage() + "</div>");
        } catch (SQLException e) {
            out.println("<div class='error'>❌ 資料庫連接失敗: " + e.getMessage() + "</div>");
            out.println("<div class='info'>📍 錯誤代碼: " + e.getErrorCode() + "</div>");
            out.println("<div class='info'>📍 SQL狀態: " + e.getSQLState() + "</div>");
        } catch (Exception e) {
            out.println("<div class='error'>❌ 其他錯誤: " + e.getMessage() + "</div>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                out.println("<div class='error'>關閉連接時發生錯誤: " + e.getMessage() + "</div>");
            }
        }
    %>
    
    <div style="margin-top: 30px; padding: 20px; background-color: #f8f9fa; border-radius: 5px;">
        <h3>🔗 快速連結</h3>
        <a href="<%= request.getContextPath() %>/spot/SpotServlet?action=listAll">測試景點列表</a> |
        <a href="<%= request.getContextPath() %>/spot/select_page.jsp">測試景點查詢</a>
    </div>
</body>
</html> 
<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%
String drivername = "com.mysql.cj.jdbc.Driver";
String URL = "jdbc:mysql://localhost:3306/toiukha?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Taipei";
String USER_NAME = "root";
String PASSWORD = "necz1X23c4V";

Connection con = null;
try {
    Class.forName(drivername);
    con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    if (con != null) {
        out.println("資料庫連線成功！可以開始查詢景點資料了！");
        
        // 測試簡單查詢
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT 1 as test");
        if (rs.next()) {
            out.println("<br>測試查詢結果：" + rs.getInt("test"));
        }
    }
} catch (Exception e) {
    out.println("連線失敗：" + e.getMessage());
} finally {
    if (con != null) con.close();
}
%>

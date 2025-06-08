<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>景點管理系統</title>
    <style>
        .nav-menu { 
            background: #f5f5f5; 
            padding: 20px; 
            border-radius: 8px; 
            margin: 20px 0; 
        }
        .nav-menu h3 { color: #333; }
        .nav-menu ul { list-style: none; padding: 0; }
        .nav-menu li { margin: 10px 0; }
        .nav-menu a { 
            display: inline-block; 
            padding: 10px 15px; 
            background: #007bff; 
            color: white; 
            text-decoration: none; 
            border-radius: 4px; 
        }
        .nav-menu a:hover { background: #0056b3; }
    </style>
</head>
<body>
    <h1>景點管理系統</h1>
    
    <div class="nav-menu">
        <h3>景點管理</h3>
        <ul>
            <li><a href="${pageContext.request.contextPath}/spot/SpotServlet?action=listAll">📋 查看所有景點</a></li>
            <li><a href="${pageContext.request.contextPath}/spot/select_page.jsp">🔍 查詢單一景點</a></li>
            <li><a href="${pageContext.request.contextPath}/spot/addSpot.jsp">➕ 新增景點</a></li>
        </ul>
    </div>
</body>
</html>

<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>系統發生錯誤 (500)</title>
    <style>
        body { font-family: Arial; background: #fff; color: #333; padding: 40px; }
        h2 { color: #c00; }
    </style>
</head>
<body>
    <h2>系統發生錯誤 (500)</h2>
    <p>很抱歉，系統發生異常，請稍後再試或聯絡管理員。</p>
    <p>錯誤訊息：${requestScope['javax.servlet.error.message']}</p>
</body>
</html>
